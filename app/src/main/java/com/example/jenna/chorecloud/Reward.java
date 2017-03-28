package com.example.jenna.chorecloud;

/**
 * Created by Jenna on 3/24/2017.
 */

public class Reward {

    private int point;
    private String name;
    private String description;
    private boolean repeat;

    /**
     * Create a new reward object where the point value is zero, the name and description are both "", and the reward does not repeat
     */
    public Reward(){
        point = 0;
        name = "";
        description="";
        repeat = false;
    }

    /**
     * Create a new reward object with the given information
     * @param p Points required to purchase the reward
     * @param n The name of the reward
     * @param d Description of the reward
     * @param b If the reward can be purchased multiple times (0 = no, 1 = yes)
     */
    public Reward(int p, String n, String d, boolean b){
        point = p;
        name = n;
        description = d;
        repeat = b;
    }

    /**
     * Get the points required to purchase the reward
     * @return The points required to purchase the reward
     */
    public int getPoint(){
        return point;
    }

    /**
     * Get the name of the reward
     * @return The name of the reward
     */
    public String getName(){
        return name;
    }

    /**
     * Get the description of the reward
     * @return The description of the reward
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get if the reward is repeating or no
     * @return 0 if reward does not repeat, 1 if the chore repeats
     */
    public boolean getRepeat(){
        return repeat;
    }

    /**
     * Change the amount of points required to purchase the reward
     * @param p New amount of points the reward costs
     */
    public void setPoint(int p){
        point = p;
    }

    /**
     * Change the name of the reward
     * @param n The new name of the reward
     */
    public void setName(String n){
        name = n;
    }

    /**
     * Change the description of the reward
     * @param d The new description of the reward
     */
    public void setDescription(String d){
        description = d;
    }

    /**
     * Change if the reward is repeated or not
     * @param b 0 if reward is not repeated, 1 if reward is repeated
     */
    public void setRepeat(boolean b){
        repeat = b;
    }
}
