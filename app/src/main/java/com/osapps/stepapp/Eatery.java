package com.osapps.stepapp;

/**
 * Created by Osaze on 6/11/15.
 */
public class Eatery {
    String name;
    String weekHours;
    String weekendHours;
    String image;
    String desc;
    //Boolean isClosed;
    //Double[] openTimes;
    //Double[] closeTimes;

    Eatery(String name, String weekHours, String weekendHours, String image, String desc,Double[] openTimes, Double[] closeTimes){

        this.name = name;
        this.weekHours = weekHours;
        this.weekendHours = weekendHours;
        this.image = image;
        this.desc = desc;
        //this.openTimes = openTimes
        //this.closeTimes = closeTimes
    }

}
