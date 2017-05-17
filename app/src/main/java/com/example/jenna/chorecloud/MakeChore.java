package com.example.jenna.chorecloud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MakeChore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_chore);

    }

    /**
     * Create a chore, and save it to the database
     * @param view The xml view where the chore info is inputed
     */
    public void onClickMakeChore (View view){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        EditText name = (EditText) findViewById(R.id.enterName);
        EditText point = (EditText) findViewById(R.id.enterPoint);
        EditText time = (EditText) findViewById(R.id.enterTime);
        EditText due = (EditText) findViewById(R.id.enterDate);
        EditText description = (EditText) findViewById(R.id.enterDescription);

        Switch repeat = (Switch) findViewById(R.id.enterRepeat);
        Boolean repeatB = repeat.isChecked();

        String nameStr = name.getText().toString();
        if(TextUtils.isEmpty(nameStr)){
            name.setError("Invalid Chore Name");
            return;
        }
        String pointStr = point.getText().toString();
        if(TextUtils.isEmpty(pointStr)){
            point.setError("Invalid Point Value");
            return;
        }
        String timeStr = time.getText().toString();
        if(TextUtils.isEmpty(timeStr)){
            time.setError("Invalid Time Amount");
            return;
        }
        String dueStr = due.getText().toString();
        if(TextUtils.isEmpty(dueStr)){
            due.setError("Invalid Due Date");
            return;
        }
        String descriptionStr = description.getText().toString();

        int pointInt = parseInt(pointStr);
        Double timeDouble = parseDouble(timeStr);
        Double dueDouble = parseDouble(dueStr);

        Chore newchore = new Chore(pointInt,nameStr,repeatB,timeDouble.doubleValue(),dueDouble.doubleValue());
        if(!descriptionStr.isEmpty()){
            newchore.setDescription(descriptionStr);
        }

        DatabaseReference newchoreRef = ref.push();
        newchoreRef.setValue(newchore);

        SendNotification(view);
        newIntent(view);
    }

    /**
     * Create a new intent where the new chore will be displayed
     * @param view The view of the parent intent
     */
    public void newIntent(View view){
        Log.d("Test", "Step 1");
        Intent i = new Intent(this, NameListChoreDisplay.class);
        Log.d("Test","Step 2");
        startActivity(i);
    }

    /**
     * Sends a notification saying that a chore was just created
     * @param view View
     */
    public void SendNotification(View view){

        // Creates an Intent for the Activity
        Intent intent = new Intent(this, NameListChoreDisplay.class);

        // Sets the Activity to start in a new, empty task
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Creates the PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Creates a Builder object.
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("ChoreCloud");
        mBuilder.setContentText("A new chore was created!");
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_report_image);
        // Puts the PendingIntent into the notification builder
        mBuilder.setContentIntent(pendingIntent);

        // Notifications are issued by sending them to the NotificationManager system service
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Passes the Notification object to the NotificationManager.
        mNotificationManager.notify(1, mBuilder.build());
    }

}
