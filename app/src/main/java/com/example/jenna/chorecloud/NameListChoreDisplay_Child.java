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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jenna on 5/17/2017.
 */

public class NameListChoreDisplay_Child extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    Context context;

    String TAG = "TESTMYAPP";

    /**
     * Creates the child's view of the Chore List. Populates the expandable list based off of firebase, and tracks the number of points
     * @param savedInstanceState Last instance that was displayed that is saved in case user wishes to return
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namelist_chore_display_child); //View (formatting) of list
        expandableListView = (ExpandableListView) findViewById(R.id.listView); //Declares the expandableListView

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        DatabaseReference refPoint = database.getReference("Points");
        refPoint.addValueEventListener(new ValueEventListener() { //Add Listener for the points
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //Update the point value displayed based off of Firebase
                int point = dataSnapshot.getValue(int.class);
                String pointsStr = Integer.toString(point);
                String print = "Points: " + pointsStr;
                TextView pointview = (TextView) findViewById(R.id.pointview);
                pointview.setText(print);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

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
                Chore chore = dataSnapshot.getValue(Chore.class);
                ;
                if (chore != null) {
                    Log.i(TAG, "Removing chore: " + chore.getName());
                    expandableListDetail.remove(chore.getName());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
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
                                updatePointsFirebase(chore);
                                Log.i(TAG, "Chore Removed");
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
     * Sends a notification saying what chore was just completed
     * @param chorename The name of the chore that was just completed
     */
    public void SendNotificationChoreComplete(String chorename) {
        // Creates an Intent for the Activity
        Intent intent = new Intent(this, NameListChoreDisplay.class);

        // Sets the Activity to start in a new, empty task
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creates the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Creates a Builder object.
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("ChoreCloud");
        mBuilder.setContentText(chorename + " was just completed!");
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_report_image);

        // Puts the PendingIntent into the notification builder
        mBuilder.setContentIntent(pendingIntent);

        // Notifications are issued by sending them to the NotificationManager system service
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Passes the Notification object to the NotificationManager.
        mNotificationManager.notify(1, mBuilder.build());
    }

    /**
     * Updates the number of points the child has based off of the chore that was just deleted (in Firebase)
     * @param c Chore that was just deleted
     */
    public void updatePointsFirebase(final Chore c) {
        Log.d("test","Step1");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference refPoint = database.getReference("Points");
        refPoint.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Long value = mutableData.getValue(Long.class);
                if (value == null) {
                    mutableData.setValue(0);
                }
                else {
                    mutableData.setValue(value + c.getPoints());
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                Log.d(TAG, "transaction:onComplete:" + databaseError);
            }
        });
    }
}
