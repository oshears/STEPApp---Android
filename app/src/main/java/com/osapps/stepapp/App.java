package com.osapps.stepapp;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.ParseException;
import android.util.Log;
import com.parse.ParseUser;
import com.parse.ParseACL;


public class App extends Application {

    public static final String ANNOUNCEMENT_GROUP_NAME = "ALL_ANNOUNCEMENTSS";


    @Override public void onCreate() {
        super.onCreate();

        System.out.println("I AM BORN!");

        ParseObject.registerSubclass(ParseAnnouncement.class);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "QaWUilnbC0lQoBcjrYXkEos4vOZYmCxoDyEXYAba", "6kM74pku4lgflvu9HQbequLoRjqJ3WA78Ci6l4VC");



        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                    System.out.println("PUSH NOTIFICATIONS WERE INITITALIZED!");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                    System.out.println("PUSH NOTIFICATIONS NOT INITITALIZED!");
                }
                System.out.println("WHAT ARE PUSH NOTIFICATIONS?! Is one above me?");
            }
        });


    }
}
