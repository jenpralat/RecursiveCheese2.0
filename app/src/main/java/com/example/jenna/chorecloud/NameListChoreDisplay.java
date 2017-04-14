package com.example.jenna.chorecloud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jenna on 4/6/2017.
 */

public class NameListChoreDisplay extends AppCompatActivity{

   /** protected void onCreate(Bundle savedInstanceState){
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
    } **/

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    /**
     * Creates the expandable list within the information within the expandablelistview class
     * @param savedInstanceState Last instance that was displayed that is saved in case user wishes to return
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namelist_chore_display); //View (formatting) of list
        expandableListView = (ExpandableListView) findViewById(R.id.listView); //Declares the expandableListView
        expandableListDetail = expandablelistview.getData(); //Populates the information within the list
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet()); //Declare the ArrayList of titles
        expandableListAdapter = new CustomExpandablelistview(this, expandableListTitle, expandableListDetail); //Declare instance of CustomExpandablelistview class
        expandableListView.setAdapter(expandableListAdapter); //Sets the adapter that will make changes to the list
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) { //Displays message when list is expanded that the list is expanded
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            /**
             * Displays message when list is collapsed
             * @param groupPosition Position of group (title)
             */
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            /**
             * Displays message that says "(title of group) -> (child string in group)", when the child is clicked on
             * @param parent  ExpandableListView that is the parent
             * @param v View
             * @param groupPosition Position of title (group) within the list
             * @param childPosition Position of child string within the title (group)
             * @param id Id
             * @return False
             */
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
