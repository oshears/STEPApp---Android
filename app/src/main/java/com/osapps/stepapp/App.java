package com.osapps.stepapp;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.ParseException;
import android.util.Log;
import android.content.Context;

public class App extends Application {

    public static final String ANNOUNCEMENT_GROUP_NAME = "ALL_ANNOUNCEMENTSS";
    private static App instance = new App();

    public App() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    @Override public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "NU52KuQGosFJb0ZBDebqcjtJaqwS1QRP7jaknnVB", "hgw0WhEQ5fGSCa6n8SQ61YchqidnKTf0YVBOObLE");

        ParseObject.registerSubclass(ParseAnnouncement.class);


        //Enable Push Notifications
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                    System.out.println("PUSH NOTIFICATIONS WERE INITITALIZED!");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        ParsePush.subscribeInBackground("Reload");






    }
}
