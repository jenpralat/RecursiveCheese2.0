package com.example.jenna.chorecloud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jenna on 4/6/2017.
 */
//
public class NameListChoreDisplay extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    Context context;

    String TAG = "TESTMYAPP";

    /**
     * Creates the expandable list within the information within the expandablelistview class
     * @param savedInstanceState Last instance that was displayed that is saved in case user wishes to return
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namelist_chore_display); //View (formatting) of list
        expandableListView = (ExpandableListView) findViewById(R.id.listView); //Declares the expandableListView

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                Chore chore;
                for (DataSnapshot dsChore : snap.getChildren()) {
                    chore = dsChore.getValue(Chore.class);
                    if (chore != null) {
                        List<String> choreList = new ArrayList<String>();
                        choreList.add("Name: " + chore.getName());
                        choreList.add("Point Value " + chore.getPoints());
                        choreList.add("Time Required " + chore.getTime());
                        choreList.add("Due in " + chore.getDeadline());
                        choreList.add("Repeating? " + chore.getRepeat());
                        choreList.add("Description " + chore.getDescription());
                        choreList.add("Chore Completed");

                        Log.i(TAG, "In onDataChange, adding chore to map " + chore.getName());
                        expandableListDetail.put(chore.getName(), choreList);
                    }
                }

                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet()); //Declare the ArrayList of titles
                expandableListAdapter = new CustomExpandablelistview(getBaseContext(), expandableListTitle, expandableListDetail); //Declare instance of CustomExpandablelistview class
                expandableListView.setAdapter(expandableListAdapter); //Sets the adapter that will make changes to the list
            }

            //@Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(TAG, "ERROR in Database: " + databaseError.getMessage());
                Log.i(TAG, "ERROR in Database: " + databaseError.getDetails());
            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "In NameListChoreDisplay.onChildAdded()");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i(TAG, "In NameListChoreDisplay.onChildChanged()");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i(TAG, "In NameListChoreDisplay.onChildRemoved()");
                Chore chore = dataSnapshot.getValue(Chore.class);;
                if (chore != null) {
                    Log.i(TAG, "Removing chore: " + chore.getName());
                    expandableListDetail.remove(chore.getName());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) { //Displays message when list is expanded that the list is expanded
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            /**
             * Displays message when list is collapsed
             * @param groupPosition Position of group (title)
             */
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            /**
             * Deletes a chore from the database when the child "Chore Completed" is clicked
             * @param parent  ExpandableListView that is the parent
             * @param v View
             * @param groupPosition Position of title (group) within the list
             * @param childPosition Position of child string within the title (group)
             * @param id Id
             * @return False
             */
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                if (expandableListAdapter.getChild(groupPosition, childPosition).equals("Chore Completed")) {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("Chores");

                    Log.i(TAG, "In setOnChildClickListener()");

                    String ChoreDelete = (String) expandableListAdapter.getChild(groupPosition, 0);
                    final String ChoreNameDelete = ChoreDelete.substring(6);
                    Log.i(TAG, ChoreNameDelete);
                    ref.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            //getUpdates(dataSnapshot);
                            Log.i(TAG, "In setOnChildClickListener().onChildAdded");
                            Chore chore = dataSnapshot.getValue(Chore.class);
                            Log.i(TAG, chore.getName());
                            if (chore.getName().equals(ChoreNameDelete)) {
                                dataSnapshot.getRef().removeValue();
                                Log.i(TAG,"Chore Removed");
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                            Log.i(TAG, "In NameListChoreDisplay.onChildRemoved()");
                            SendNotificationChoreComplete(ChoreNameDelete);
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                return false;
            }
        });
    }

    /**
     * Go to the Make Chore intent
     * @param view View of the new intent
     */
    public void onClickMakeChore(View view){
        Intent i = new Intent(this, MakeChore.class);
        startActivity(i);
    }

    /**
     * Sends a notification saying what chore was just completed
     * @param chorename The name of the chore that was just completed
     */
    public void SendNotificationChoreComplete(String chorename){
        // Creates an Intent for the Activity
        Intent intent = new Intent(this, NameListChoreDisplay.class);

        // Sets the Activity to start in a new, empty task
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creates the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Creates a Builder object.
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("ChoreCloud");
        mBuilder.setContentText(chorename+" was just completed!");
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_report_image);

        // Puts the PendingIntent into the notification builder
        mBuilder.setContentIntent(pendingIntent);

        // Notifications are issued by sending them to the NotificationManager system service
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Passes the Notification object to the NotificationManager.
        mNotificationManager.notify(1, mBuilder.build());
    }
}

