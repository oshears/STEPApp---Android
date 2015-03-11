package com.osapps.stepapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.parse.*;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class AnnouncementList extends ActionBarActivity {

    private LayoutInflater inflater;
    private ParseQueryAdapter<ParseAnnouncement> parseAnnouncementAdapter;

    private ListView announcementListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_list);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        announcementListView = (ListView) findViewById(R.id.announcementListView);

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<ParseAnnouncement> factory = new ParseQueryAdapter.QueryFactory<ParseAnnouncement>() {
            public ParseQuery<ParseAnnouncement> create() {
                ParseQuery<ParseAnnouncement> query = ParseAnnouncement.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };

        // Set up the adapter
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parseAnnouncementAdapter = new ParseAnnouncementAdapter(this, factory);


        // Attach the query adapter to the view
        ListView announcementListView = (ListView) findViewById(R.id.announcementListView);
        announcementListView.setAdapter(parseAnnouncementAdapter);

        parseAnnouncementAdapter.loadObjects();
        loadFromParse();


    }

    @Override
    protected  void onResume(){
        super.onResume();
        parseAnnouncementAdapter.loadObjects();
        loadFromParse();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_announcement_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadFromParse() {
        ParseQuery<ParseAnnouncement> query = ParseAnnouncement.getQuery();
        query.findInBackground(new FindCallback<ParseAnnouncement>() {
            public void done(List<ParseAnnouncement> announcements, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<ParseAnnouncement>) announcements,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    System.out.println("Got an error at the inner");
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            parseAnnouncementAdapter.loadObjects();
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
    private class ParseAnnouncementAdapter extends ParseQueryAdapter<ParseAnnouncement> {

        public ParseAnnouncementAdapter(Context context,
                                        ParseQueryAdapter.QueryFactory<ParseAnnouncement> queryFactory) {
            super(context, queryFactory);
        }

        @Override
        public View getItemView(ParseAnnouncement announcement, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.announcement_list_item, parent, false);
                holder = new ViewHolder();
                holder.announcementTitle = (TextView) view
                        .findViewById(R.id.annoncement_title);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            TextView announcementTitle = holder.announcementTitle;
            announcementTitle.setText(announcement.getTitle());

            return view;
        }
    }

    private static class ViewHolder {
        TextView announcementTitle;
    }
}

