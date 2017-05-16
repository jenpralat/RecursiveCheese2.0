package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by rithi on 5/16/2017.
 */
public class chooseone extends AppCompatActivity {

    //Display the new chore
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_one);
    }
    public void onClick(View view){

        Intent a = new Intent(this, NameListChoreDisplay.class);
        startActivity(a);
    }
}