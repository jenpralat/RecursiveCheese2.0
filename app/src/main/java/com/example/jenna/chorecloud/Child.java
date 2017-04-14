package com.example.jenna.chorecloud;
import java.util.ArrayList;

/*
 * The Child class uses child name, number of points, an arraylist of chores,
 * and an arraylist of the rewards the child can unlock.
 * Created by Jenna on 3/29/2017.
 */

public class Child {
    // Data
    private String childName;
    private int points;
    private ArrayList<Chore> chores = new ArrayList<Chore>();
    private ArrayList<Reward> rewards = new ArrayList<Reward>();

    // Constructor

    /*
     * Creates an instance of the child class with a name, number of point, and an arrayList of chores
     * * string value of the child's name
     * integer value of points
     * arraylist of chores
     * arraylist of rewards
     */
    public Child(String Name, int Points) {
        childName = Name;
        points = Points;
        chores = new ArrayList<Chore>();
        rewards = new ArrayList<Reward>();
    }

    public Child() {
        childName = "David E Buger";
        points = 0;
        chores = new ArrayList<Chore>();
        rewards = new ArrayList<Reward>();
    }
    /*
     * Creates an instance of the child class with a name of David E Buger, and with zero points
     */
    public String getName() {
        return this.childName;
    }
    /*
     * @return the child's name
     */
    public int getPoints() {
        return this.points;
    }
    /*
     * @return the amount of points the child has
     */

    public ArrayList getChores() {
        return this.chores;
    }
    /*
     * @return the list of chores assigned to the child
     */
    public ArrayList getRewards() {
        return this.rewards;
        }
        /*
         * @return the list of rewards the child can unlock
         */


    public void setName(String name) {this.childName = name;
    }
      /*
     * Updates the child's name
     */
      public void setPoints(int addedPoints) {this.points += addedPoints;
      }
      /*
     * Updates the child's point totals.
     */
}
