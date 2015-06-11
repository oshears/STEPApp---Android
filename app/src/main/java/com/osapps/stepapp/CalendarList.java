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
import android.widget.ListView;
import com.parse.*;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Osaze on 6/10/15.
 */
public class CalendarList extends ActionBarActivity {

    private LayoutInflater inflater;
    private ParseQueryAdapter<ParseCalendar> parseCalendarAdapter;

    private ListView calendarListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_list);

        final CalendarList caller = this;

        calendarListView = (ListView) findViewById(R.id.calendarListView);

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<ParseCalendar> factory = new ParseQueryAdapter.QueryFactory<ParseCalendar>() {
            public ParseQuery<ParseCalendar> create() {
                ParseQuery<ParseCalendar> query = ParseCalendar.getQuery();
                query.orderByDescending("createdAt");
                query.fromLocalDatastore();
                return query;
            }
        };

        // Set up the adapter
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        parseCalendarAdapter = new ParseCalendarAdapter(this, factory);


        // Attach the query adapter to the view
        final ListView calendarListView = (ListView) findViewById(R.id.calendarListView);
        calendarListView.setAdapter(parseCalendarAdapter);

        parseCalendarAdapter.loadObjects();
        loadFromParse();

        calendarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                final int pos = position;
                // Open the edit entry activity
                ParseCalendar clicked = (ParseCalendar) calendarListView.getItemAtPosition(position);
                viewDetailedCalendar(caller, clicked);
            }
        });




    }

    @Override
    protected void onResume(){
        super.onResume();
        parseCalendarAdapter.loadObjects();
        loadFromParse();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_list, menu);
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
        if (id == R.id.action_refresh) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void viewDetailedCalendar(CalendarList caller, ParseCalendar calendar){
        Intent viewCalendarDetails = new Intent(caller,CalendarDetail.class);
        viewCalendarDetails.putExtra("calendarTitle",calendar.getTitle());
        viewCalendarDetails.putExtra("calendarDate",calendar.getPosttime());
        viewCalendarDetails.putExtra("calendarContent",calendar.getConent());
        startActivityForResult(viewCalendarDetails,1);
    }
    private void loadFromParse() {
        ParseQuery<ParseCalendar> query = ParseCalendar.getQuery();
        query.findInBackground(new FindCallback<ParseCalendar>() {
            public void done(List<ParseCalendar> calendars, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<ParseCalendar>) calendars,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    System.out.println("Got an error at the inner");
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            parseCalendarAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("CalendarActivity",
                                                "Error pinning calendars: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    System.out.println("Got an error at the outer");
                    Log.i("CalendarActivity",
                            "loadFromParse: Error finding pinned calendars: "
                                    + e.getMessage());
                }
            }
        });
    }
    private class ParseCalendarAdapter extends ParseQueryAdapter<ParseCalendar> {

        public ParseCalendarAdapter(Context context,
                                        ParseQueryAdapter.QueryFactory<ParseCalendar> queryFactory) {
            super(context, queryFactory);
        }

        @Override
        public View getItemView(ParseCalendar calendar, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = inflater.inflate(R.layout.calendar_list_item, parent, false);
                holder = new ViewHolder();
                holder.calendarTitle = (TextView) view
                        .findViewById(R.id.calendar_title);
                holder.calendarDate = (TextView) view.findViewById(R.id.calendar_date);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            TextView calendarTitle = holder.calendarTitle;
            TextView calendarDate = holder.calendarDate;

            calendarTitle.setText(calendar.getTitle());
            calendarDate.setText(calendar.getPosttime());

            return view;
        }
    }

    private static class ViewHolder {
        TextView calendarTitle;
        TextView calendarContent;
        TextView calendarDate;
    }

}
