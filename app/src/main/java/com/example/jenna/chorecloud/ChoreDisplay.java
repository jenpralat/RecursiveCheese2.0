package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static java.lang.Boolean.FALSE;

/**
 * Created by rithi on 3/31/2017.
 */

public class ChoreDisplay extends AppCompatActivity {

    //Display the new chore
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chore_display);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String name = intent.getStringExtra("ChoreNameDisplay");
        int point = intent.getIntExtra("ChorePointDisplay", 0);
        double time = intent.getDoubleExtra("ChoreTimeDisplay", 0);
        String desc = intent.getStringExtra("ChoreDescriptionDisplay");
        boolean repeat = intent.getBooleanExtra("ChoreRepeatDisplay",FALSE);
        double deadline = intent.getDoubleExtra("ChoreDeadlineDisplay", 0);

        // Capture the layout's TextViews
        TextView ChoreNameView = (TextView) findViewById(R.id.ChoreName);
        TextView PointValueView = (TextView) findViewById(R.id.PointValue);
        TextView TimeReqView = (TextView) findViewById(R.id.Time);
        TextView DescriptionView = (TextView) findViewById(R.id.Description);
        TextView RepeatView = (TextView) findViewById(R.id.Repeat);
        TextView DeadlineView = (TextView) findViewById(R.id.DueDate);

        //Fill in the text views with the correct strings
        ChoreNameView.setText(name);
        PointValueView.setText(Integer.toString(point));
        TimeReqView.setText(Double.toString(time));
        DescriptionView.setText(desc);
        RepeatView.setText(Boolean.toString(repeat));
        DeadlineView.setText(Double.toString(deadline));

    }

    //Go to home intent that displays the list of chores
    public void onClick(View view){
        Intent i = new Intent(this, NameListChoreDisplay.class);
        startActivity(i);
    }

}
