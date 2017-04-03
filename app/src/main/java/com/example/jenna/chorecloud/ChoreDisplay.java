package com.example.jenna.chorecloud;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by rithi on 3/31/2017.
 */

public class ChoreDisplay extends AppCompatActivity {

    TextView name = (TextView)findViewById(R.id.ChoreName);
    TextView point = (TextView)findViewById(R.id.PointValue);
    TextView time = (TextView)findViewById(R.id.Time);
    TextView repeat = (TextView)findViewById(R.id.Repeat);
    TextView duedate = (TextView)findViewById(R.id.DueDate);
    TextView description = (TextView)findViewById(R.id.Description);
}
