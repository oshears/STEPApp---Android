package com.osapps.stepapp;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.parse.ParseException;
import android.util.Log;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "QaWUilnbC0lQoBcjrYXkEos4vOZYmCxoDyEXYAba", "6kM74pku4lgflvu9HQbequLoRjqJ3WA78Ci6l4VC");


        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

    }
}
