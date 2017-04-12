package com.example.jenna.chorecloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jenna on 4/7/2017.
 */

public class expandablelistview {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> ChoreOne = new ArrayList<String>();
        ChoreOne.add("Name");
        ChoreOne.add("Point Value");
        ChoreOne.add("Time Required");
        ChoreOne.add("Due in");
        ChoreOne.add("Repeating?");
        ChoreOne.add("Description");

        List<String> ChoreTwo = new ArrayList<String>();
        ChoreTwo.add("Name");
        ChoreTwo.add("Point Value");
        ChoreTwo.add("Time Required");
        ChoreTwo.add("Due in");
        ChoreTwo.add("Repeating?");
        ChoreTwo.add("Description");

        List<String> ChoreThree = new ArrayList<String>();
        ChoreThree.add("Name");
        ChoreThree.add("Point Value");
        ChoreThree.add("Time Required");
        ChoreThree.add("Due in");
        ChoreThree.add("Repeating?");
        ChoreThree.add("Description");

        expandableListDetail.put("Chore one", ChoreOne);
        expandableListDetail.put("Chore two", ChoreTwo);
        expandableListDetail.put("Chore three", ChoreThree);
        return expandableListDetail;
    }
}
