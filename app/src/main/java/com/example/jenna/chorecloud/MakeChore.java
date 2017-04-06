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

public class MakeChore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_chore);
    }

    public void MakeChore(View view) {
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

        Chore chore = new Chore(pointInt, nameStr, timeDouble, descriptionStr, dueDouble, repeatB);

        Intent intent = new Intent(this, ChoreDisplay.class);
        String nameDisplay = chore.getName();
        intent.putExtra("ChoreNameDisplay", nameDisplay);
        startActivity(intent);
    }
    public void SendNotification(View view) {

        //Create the intent that’ll fire when the user taps the notification//
        Intent intent = new Intent(this, ChoreDisplay.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //Get an instance of Notification Manager
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("ChoreCloud");
        mBuilder.setContentText("A new chore was created!");
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_report_image);
        mBuilder.setContentIntent(pendingIntent);

        // Gets an instance of the NotificationManager service//
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());



    }

}


