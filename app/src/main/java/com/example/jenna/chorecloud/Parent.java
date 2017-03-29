package com.example.jenna.chorecloud;
import java.util.ArrayList;

/**
 * Created by Jonathan Sigel on 3/29/2017.
 */

public class Parent {
    public ArrayList<Child> children = new ArrayList<Child>();
    public ArrayList<Reward> rewardsClaimed = new ArrayList<Reward>();

    public Parent()
    {
        children = new ArrayList<Child>();
        rewardsClaimed = new ArrayList<Reward>();
    }
}
