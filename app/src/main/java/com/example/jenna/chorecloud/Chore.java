package com.example.jenna.chorecloud;

/**
 * Created by Jenna on 3/24/2017.
 */

public class Chore {

        private int points;
        private String name;
        private double time;
        private String description;
        private double deadline;
        private boolean repeat;
//CONSTRUCTORS
        /**
         * An empty constructor for the chore class.
         */
        public Chore() {}

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
            this.points = points;
            this.name = name;
            this.time = time;
            this.description = desc;
            deadline = due;
            this.repeat = repeat;
        }

        public Chore(int points, String name, boolean repeat, double time, double due){
            this.points = points;
            this.name = name;
            this.time = time;
            this.description = "-";
            deadline = due;
            this.repeat = repeat;
        }
//GETTERS
        /**
         * A getter for the point value of the chore.
         * @return The number of points.
         */
        public int getPoints()
        {
            return this.points;
        }
        /**
         * A getter for the name of the chore.
         * @return The name of the chore.
         */
        public String getName()
        {
            return this.name;
        }
        /**
         * A getter for the duration time of the chore.
         * @return The duration time of the chore.
         */
        public double getTime(){return this.time;}
        /**
         * A getter for the chore's description.
         * @return The description of the chore.
         */
        public String getDescription()
        {
            return this.description;
        }
        /**
         * A getter for the chore's deadline.
         * @return The deadline.
         */
        public double getDeadline(){return this.deadline;}
        /**
         * A getter for whether or not the chore should repeat.
         * @return The deadline.
         */
        public boolean getRepeat()
        {
            return this.repeat;
        }
//SETTERS
        /**
         * Sets the value of the chore.
         * @param points, how many points the chore is worth.
         */
       public void setPoints(int points)
        {
            this.points = points;
        }
        /**
         * Sets the amount of time the chore should take.
         * @param time, the reccomended duration.
         */
        public void setTime(double time)
        {
            this.time = time;
        }
        /**
         * Sets the description of the chore.
         * @param desc, the chore's description.
         */
        public void setDescription(String desc)
        {
            this.description = desc;
        }
        /**
         * Sets the deadline of the chore.
         * @param due, in how many time units its due.
         */
        public void setDeadline(double due)
        {
            this.deadline = due;
        }
       /**
         * Sets whether or not the chore repeats.
         * @param repeat, true = it repeats, false = it doesn't.
         */
       public void setRepeat(boolean repeat)
        {
            this.repeat = repeat;
        }

        /**
        * Sets the chore name
        * @param name Name of the chore
        */
        public void setName (String name)
        {
            this.name = name;
        }

}
