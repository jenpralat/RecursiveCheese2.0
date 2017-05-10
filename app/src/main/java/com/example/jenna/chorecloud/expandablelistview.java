package com.example.jenna.chorecloud;

import android.util.Log;

import com.firebase.client.FirebaseError;
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
   private static HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
    /**
     * Create the HashMap of strings that will be displayed under the headers in the list
     * @return an expandableListDetail that has the strings that will be displayed in the
     */
    public static HashMap<String, List<String>> getData() {

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged (DataSnapshot dataSnapshot, String prevChildKey){
                getUpdates(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return expandableListDetail;
    }

    public static void getUpdates (DataSnapshot ds){
        Chore chore;
        for (DataSnapshot data : ds.getChildren()){
            chore = ds.getValue(Chore.class);
            if(chore != null) {
                List<String> choreList = new ArrayList<String>();
                choreList.add("Name: " + chore.getName());
                choreList.add("Point Value" + chore.getPoints());
                choreList.add("Time Required" + chore.getTime());
                choreList.add("Due in" + chore.getDeadline());
                choreList.add("Repeating?" + chore.getRepeat());
                choreList.add("Description" + chore.getDescription());

                expandableListDetail.put(chore.getName(), choreList);
            }
        }
    }

    public static HashMap<String, List<String>> getData2() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Chores");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getUpdates(dataSnapshot);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return expandableListDetail;
    }
}
