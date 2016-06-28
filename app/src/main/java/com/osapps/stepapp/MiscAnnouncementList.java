package com.osapps.stepapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by Osaze on 6/10/15.
 */
public class MiscAnnouncementList extends ActionBarActivity {

    private LayoutInflater inflater;
    private ParseQueryAdapter<ParseMiscAnnouncement> parseMiscAnnouncementAdapter;

    private ListView miscAnnouncementListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous_list);

        final MiscAnnouncementList caller = this;

        miscAnnouncementListView = (ListView) findViewById(R.id.miscAnnouncementListView);

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<ParseMiscAnnouncement> factory = new ParseQueryAdapter.QueryFactory<ParseMiscAnnouncement>() {
            public ParseQuery<ParseMiscAnnouncement> create() {
                ParseQuery<ParseMiscAnnouncement> query = ParseMiscAnnouncement.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };

        // Set up the adapter
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parseMiscAnnouncementAdapter = new ParseMiscAnnouncementAdapter(this, factory);


        // Attach the query adapter to the view
        final ListView miscAnnouncementListView = (ListView) findViewById(R.id.miscAnnouncementListView);
        miscAnnouncementListView.setAdapter(parseMiscAnnouncementAdapter);

        parseMiscAnnouncementAdapter.loadObjects();
        loadFromParse();
        parseMiscAnnouncementAdapter.notifyDataSetChanged();


    }

    @Override
    protected void onResume(){
        super.onResume();
        parseMiscAnnouncementAdapter.loadObjects();
        loadFromParse();
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
            return true;
        }
        if (id == R.id.action_announcements){
            Intent announcements = new Intent(this,AnnouncementList.class);
            startActivity(announcements);
            return true;
        }
        if (id == R.id.action_calendar) {
            Intent calendarView = new Intent(this,CalendarList.class);
            startActivity(calendarView);
            return true;
        }
        if (id == R.id.action_people) {
            Intent peopleView = new Intent(this,PeopleList.class);
            startActivity(peopleView);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadFromParse() {
        ParseQuery<ParseMiscAnnouncement> query = ParseMiscAnnouncement.getQuery();
        query.findInBackground(new FindCallback<ParseMiscAnnouncement>() {
            public void done(List<ParseMiscAnnouncement> miscAnnouncements, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<ParseMiscAnnouncement>) miscAnnouncements,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    System.out.println("Got an error at the inner");
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            parseMiscAnnouncementAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("AnnouncementActivity",
                                                "Error pinning announcements: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    System.out.println("Got an error at the outer");
                    Log.i("AnnouncementActivity",
                            "loadFromParse: Error finding pinned announcements: "
                                    + e.getMessage());
                }
            }
        });
    }
    private class ParseMiscAnnouncementAdapter extends ParseQueryAdapter<ParseMiscAnnouncement> {

        public ParseMiscAnnouncementAdapter(Context context,
                                        ParseQueryAdapter.QueryFactory<ParseMiscAnnouncement> queryFactory) {
            super(context, queryFactory);
        }

        @Override
        public View getItemView(ParseMiscAnnouncement miscAnnouncement, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.miscellaneous_list_item, parent, false);
                holder = new ViewHolder();
                holder.miscAnnouncementContent = (TextView) view
                        .findViewById(R.id.miscAnnouncement_content);
                holder.miscAnnouncementDate = (TextView) view.findViewById(R.id.miscAnnouncement_date);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            TextView miscAnnouncementContent = holder.miscAnnouncementContent;
            TextView miscAnnouncementDate = holder.miscAnnouncementDate;

            miscAnnouncementContent.setText(miscAnnouncement.getConent());
            miscAnnouncementDate.setText(miscAnnouncement.getPosttime());
            return view;
        }
    }

    private static class ViewHolder {
        TextView miscAnnouncementContent;
        TextView miscAnnouncementDate;
    }
}
