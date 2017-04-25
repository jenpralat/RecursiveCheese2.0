package com.example.jenna.chorecloud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MakeChore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_chore);

    }

    public void MakeChore (View view){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        EditText name = (EditText) findViewById(R.id.enterName);
        EditText point = (EditText) findViewById(R.id.enterPoint);
        EditText time = (EditText) findViewById(R.id.enterTime);
        EditText due = (EditText) findViewById(R.id.enterDate);
        EditText description = (EditText) findViewById(R.id.enterDescription);

        Switch repeat = (Switch) findViewById(R.id.enterRepeat);
        Boolean repeatB = repeat.isChecked();

        String nameStr = name.getText().toString();
        String pointStr = point.getText().toString();
        String timeStr = time.getText().toString();
        String dueStr = due.getText().toString();
        String descriptionStr = description.getText().toString();

        int pointInt = parseInt(pointStr);
        Double timeDouble = parseDouble(timeStr);
        Double dueDouble = parseDouble(dueStr);

        Chore newchore = new Chore(pointInt, nameStr, timeDouble, descriptionStr, dueDouble, repeatB);

        DatabaseReference choreRef = ref.child("Chores");
        DatabaseReference newchoreRef = choreRef.push();
        newchoreRef.setValue(newchore);

        SendNotification(view);
        newIntent(view, newchore);
    }

    public void newIntent(View view, Chore chore){
        Intent intent = new Intent(this, ChoreDisplay.class);
        String nameDisplay = chore.getName();
        int pointDisplay = chore.getPoints();
        Double timeDisplay = chore.getTime();
        String descriptionDisplay = chore.getDesc();
        Boolean repeatDisplay = chore.getRepeat();
        Double deadlineDisplay = chore.getDeadline();
        intent.putExtra("ChoreNameDisplay",nameDisplay);
        intent.putExtra("ChorePointDisplay",pointDisplay);
        intent.putExtra("ChoreTimeDisplay", timeDisplay);
        intent.putExtra("ChoreDescriptionDisplay", descriptionDisplay);
        intent.putExtra("ChoreRepeatDisplay", repeatDisplay);
        intent.putExtra("ChoreDeadlineDisplay", deadlineDisplay);
        startActivity(intent);
    }

    public void SendNotification(View view){

        // Creates an Intent for the Activity
        Intent intent = new Intent(this, ChoreDisplay.class);

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
