package com.osapps.stepapp;

import com.parse.*;

import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

@ParseClassName("CalendarDay")
public class ParseCalendar extends ParseObject {
    public String getDate(){

        Date date = getDate("date");
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date);//Thursday
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", date); //Jun
        String intMonth = (String) android.text.format.DateFormat.format("MM", date); //06
        String year = (String) android.text.format.DateFormat.format("yyyy", date); //2013
        String day = (String) android.text.format.DateFormat.format("dd", date); //20

        return  dayOfTheWeek+", "+stringMonth+" "+day+", "+year;
    }
    public String getDay(){
        Date date = getDate("date");


        String day = (String) android.text.format.DateFormat.format("dd", date);
        return day;
    }
    public String getMonth(){
        Date date = getDate("date");
        String stringMonth = (String) android.text.format.DateFormat.format("MMMM", date);

        return stringMonth;
    }
    public String getTopic(){
        return getString("topic");
    }
    public String getActivity(int x){
        return getString("main_activity_"+x);
    }
    public static ParseQuery<ParseCalendar> getQuery() {
        return ParseQuery.getQuery(ParseCalendar.class);
    }

}
