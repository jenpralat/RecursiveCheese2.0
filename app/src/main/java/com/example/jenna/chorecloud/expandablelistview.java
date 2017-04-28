package com.example.jenna.chorecloud;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class expandablelistview {

    /**
     * Create the HashMap of strings that will be displayed under the headers in the list
     * @return an expandableListDetail that has the strings that will be displayed in the
     */
    public static HashMap<String, List<String>> getData() {

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        final ArrayList<Chore> choresfromfirebase = new ArrayList<Chore>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Chore newChore = dataSnapshot.getValue(Chore.class);
                choresfromfirebase.add(newChore);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
/**
        Chore chore = choresfromfirebase.get(0);
        List<String> ChoreOne = new ArrayList<String>();
        ChoreOne.add("Name: "+chore.getName());
        ChoreOne.add("Point Value: "+chore.getPoints());
        ChoreOne.add("Time Required: "+ chore.getTime());
        ChoreOne.add("Due in");
        ChoreOne.add("Repeating?");
        ChoreOne.add("Description");
*//**
        chore = choresfromfirebase.get(1);
        List<String> ChoreTwo = new ArrayList<String>();
        ChoreTwo.add("Name: "+chore.getName());
        ChoreTwo.add("Point Value");
        ChoreTwo.add("Time Required");
        ChoreTwo.add("Due in");
        ChoreTwo.add("Repeating?");
        ChoreTwo.add("Description");
*/
        List<String> ChoreThree = new ArrayList<String>();
        ChoreThree.add("Name");
        ChoreThree.add("Point Value");
        ChoreThree.add("Time Required");
        ChoreThree.add("Due in");
        ChoreThree.add("Repeating?");
        ChoreThree.add("Description");

        //expandableListDetail.put("Chore one", ChoreOne);
       // expandableListDetail.put("Chore two", ChoreTwo);
        expandableListDetail.put("Chore three", ChoreThree);
        return expandableListDetail;
    }
}
