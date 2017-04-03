package com.example.jenna.chorecloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

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
    }

    public void toChoreDisplay(View view) {

    }
}

