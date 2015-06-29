package com.osapps.stepapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.parse.*;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class PeopleList extends ActionBarActivity {

    private LayoutInflater inflater;

    private ListView peopleListView;

    private Person[] peopleArray = new Person[0];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        People allPeeps = new People();
        peopleArray = allPeeps.people;
        setContentView(R.layout.activity_people_list);
        final PeopleList caller = this;
        peopleListView = (ListView) findViewById(R.id.peopleListView);

        updatePeopleList();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_miscellaneous) {
            Intent miscPeoples = new Intent(this,MiscAnnouncementList.class);
            startActivity(miscPeoples);
            return true;
        }
        if (id == R.id.action_announcements) {
            Intent announcementView = new Intent(this,AnnouncementList.class);
            startActivity(announcementView);
            return true;
        }
        if (id == R.id.action_calendar) {
            Intent calendarView = new Intent(this,CalendarList.class);
            startActivity(calendarView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void updatePeopleList(){


        // Populate the list, through the adapter

        peopleListView = (ListView) findViewById(R.id.peopleListView);
        final PersonEntryAdapter peopleEntryAdapter = new PersonEntryAdapter(this, R.layout.people_list_item);
        peopleListView.setAdapter(peopleEntryAdapter);
        /*for(final NewsEntry entry : getNewsEntries()) {
            newsEntryAdapter.add(entry);
        }*/
        //ArrayList<String> courseNames = new ArrayList<String>();
        peopleEntryAdapter.addAll(peopleArray);

    }

}

