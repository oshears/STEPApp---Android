package com.osapps.stepapp;


public class Announcement {
    String name = "";
    String date = "";
    String content = "";


    public Announcement(String name, String date, String content){
        this.name=name;
        this.date = date;
        this.content = content;
    }

    @Override
    public String toString(){
        return "Title: " + name + " Date: " + date + " Content " + content;

    }
}
