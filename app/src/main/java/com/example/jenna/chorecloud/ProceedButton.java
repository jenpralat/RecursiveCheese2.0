package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Jonathan Sigel on 5/8/2017.
 */

public class ProceedButton extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google);
    }
    public void onClick(View view){
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}
