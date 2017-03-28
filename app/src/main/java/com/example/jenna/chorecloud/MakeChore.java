package com.example.jenna.chorecloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class MakeChore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_chore);
    }

    public void MakeChore(){

        //Need to figure out switch

        EditText name = (EditText) findViewById(R.id.enterName);
        EditText point = (EditText) findViewById(R.id.enterPoint);
        EditText time = (EditText) findViewById(R.id.enterTime);
        EditText due = (EditText) findViewById(R.id.enterDate);
        EditText description = (EditText) findViewById(R.id.enterDescription);

        String nameStr = name.getText().toString();
        String pointStr = point.getText().toString();
        String timeStr = time.getText().toString();
        String dueStr = due.getText().toString();
        String descriptionStr = description.getText().toString();

        int pointInt = parseInt(pointStr);
        Double timeDouble = parseDouble(timeStr);
        Double dueDouble = parseDouble(dueStr);
    }

}
