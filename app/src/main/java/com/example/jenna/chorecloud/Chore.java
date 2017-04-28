package com.example.jenna.chorecloud;

/**
 * Created by Jenna on 3/24/2017.
 */

public class Chore {

        private int pointValue;
        private String choreName;
        private double choreTime;
        private String choreDescription;
        private double choreDeadline;
        private boolean choreRepeat;
//CONSTRUCTORS
        /**
         * An empty constructor for the chore class.
         */
        public Chore()
        {
            pointValue = 0;
            choreName = "";
            choreTime = 0;
            choreDescription = "";
            choreDeadline = 0;
            choreRepeat = false;
        }
        /* A constructor for the chore class.
         * @param points: The amount of points the chore is worth. (int)
         * @param name: The name of the chore. (string)
         * @param time: Approximately how long the chore will take. (double)
         * @param desc: Description of the chore. (string)
         * @param due: How much time until the chore is due. (double)
         * @param repeat: Whether or not its to be repeated. (boolean)
         */
        public Chore(int points, String name, double time, String desc, double due, boolean repeat)
        {
            pointValue = points;
            choreName = name;
            choreTime = time;
            choreDescription = desc;
            choreDeadline = due;
            choreRepeat = repeat;
        }

        public Chore(int points, String name, double time, double due, boolean repeat){
            pointValue = points;
            choreName = name;
            choreTime = time;
            choreDescription = "-";
            choreDeadline = due;
            choreRepeat = repeat;
        }
//GETTERS
        /**
         * A getter for the point value of the chore.
         * @return The number of points.
         */
        public int getPoints()
        {
            return this.pointValue;
        }
        /**
         * A getter for the name of the chore.
         * @return The name of the chore.
         */
        public String getName()
        {
            return this.choreName;
        }
        /**
         * A getter for the duration time of the chore.
         * @return The duration time of the chore.
         */
        public double getTime()
        {
            return this.choreTime;
        }
        /**
         * A getter for the chore's description.
         * @return The description of the chore.
         */
        public String getDesc()
        {
            return this.choreDescription;
        }
        /**
         * A getter for the chore's deadline.
         * @return The deadline.
         */
        public Double getDeadline()
        {
            return this.choreDeadline;
        }
        /**
         * A getter for whether or not the chore should repeat.
         * @return The deadline.
         */
        public boolean getRepeat()
        {
            return this.choreRepeat;
        }
//SETTERS
        /**
         * Sets the value of the chore.
         * @param points, how many points the chore is worth.
         */
        public void setPoints(int points)
        {
            pointValue = points;
        }
        /**
         * Renames the chore.
         * @param name, The name of the chore.
         */
        public void getName(String name)
        {
            choreName = name;
        }
        /**
         * Sets the amount of time the chore should take.
         * @param time, the reccomended duration.
         */
        public void setTime(double time)
        {
            choreTime = time;
        }
        /**
         * Sets the description of the chore.
         * @param desc, the chore's description.
         */
        public void setDesc(String desc)
        {
            choreDescription = desc;
        }
        /**
         * Sets the deadline of the chore.
         * @param due, in how many time units its due.
         */
        public void setDeadline(double due)
        {
            choreDeadline = due;
        }
        /**
         * Sets whether or not the chore repeats.
         * @param repeat, true = it repeats, false = it doesn't.
         */
        public void setRepeat(boolean repeat)
        {
            choreRepeat = repeat;
        }
}
