package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jenna on 4/6/2017.
 */

public class NameListChoreDisplay extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namelist_chore_display);
    }

    public void newDetailedIntent(View view, Chore chore){
        Intent i = new Intent(this, ChoreDisplay.class);
        String nameDisplay = "Wash the Dishes";
        int pointDisplay = 999;
        //intent.putExtra("ChoreNameDisplay",nameDisplay);
        //intent.putExtra("ChorePointDisplay",pointDisplay);
        startActivity(i);
    }
}
