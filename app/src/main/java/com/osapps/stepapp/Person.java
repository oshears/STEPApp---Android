package com.osapps.stepapp;

import android.text.Html.ImageGetter;
import android.text.Html;
import android.graphics.drawable.*;

/**
 * Created by Osaze on 6/19/15.
 */
public class Person {


    String name;
    String bio;
    String image;
    String role;
    int imageID;

    Person(String name, String bio, String image, String role){
        this.name = name;
        this.bio = bio;
        this.image = image;
        this.role = role;
        this.imageID = getImageID();
    }

    public int getImageID(){

        //r.
       int id=0;
       if (image.equals("alex")) {
           id = R.drawable.alexandro;
       }
       else if (image.equals("danielle")) {
            id = R.drawable.danielle;
       }
       else if (image.equals("ebi")) {
           id = R.drawable.ebi;
       }
       else if (image.equals("felicia")) {
           id = R.drawable.felicia;
       }
       else if (image.equals("garey")) {
           id = R.drawable.garey;
       }
       else if (image.equals("jalen")) {
           id = R.drawable.jalen;
       }
       else if (image.equals("jennifer")) {
           id = R.drawable.jennifer;
       }
       else if (image.equals("jumana")) {
           id = R.drawable.jumana;
       }
       else if (image.equals("kristi")) {
           id = R.drawable.kristi;
       }
       else if (image.equals("olivia")) {
           id = R.drawable.olivia;
       }
       else if (image.equals("osaze")) {
           id = R.drawable.osaze;
       }
       else if (image.equals("rebecca")) {
           id = R.drawable.rebecca;
       }
       else if (image.equals("rodrigo")) {
           id = R.drawable.rodrigo;
       }
       else if (image.equals("shanice")) {
           id = R.drawable.shanice;
       }
       else if (image.equals("shaoxian")) {
           id = R.drawable.shaoxian;
       }
       else if (image.equals("sonia")) {
           id = R.drawable.sonia;
       }
       else if (image.equals("teejay")) {
           id = R.drawable.teejay;
       }
       else if (image.equals("vivi")) {
           id = R.drawable.vivi;
       }

        /*
        Drawable d = getResources().getDrawable(id);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        return d;
        */
        return id;


    }
}
