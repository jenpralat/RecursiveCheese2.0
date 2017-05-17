package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by rithi on 5/16/2017.
 */
public class ChooseChild extends AppCompatActivity {

    //Creates the view
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_one);
    }
    /**
     * Sends the child to the correct list view
     * @param view View
     */
    public void onClickChild (View view){
        Intent ab = new Intent(this, NameListChoreDisplay_Child.class);
        startActivity(ab);
    }

    /**
     * Sends the parent to the correct list view
     * @param view View
     */
    public void onClickParent (View view){
        Intent intent = new Intent(this, GoogleSigninActivity.class);
        startActivity(intent);
    }
}