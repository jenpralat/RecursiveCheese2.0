package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by rithi on 3/31/2017.
 */

public class ChoreDisplay extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chore_display);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra("ChoreNameDisplay");
        int point = intent.getIntExtra("ChorePointDisplay", 0);

        // Capture the layout's TextView and set the string as its text
        TextView ChoreNameView = (TextView) findViewById(R.id.ChoreName);
        TextView PointValueView = (TextView) findViewById(R.id.PointValue);
        ChoreNameView.setText(message);
    }

    public void onClick(View view){
        Intent i = new Intent(this, NameListChoreDisplay.class);
        startActivity(i);
    }

}
