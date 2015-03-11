package com.osapps.stepapp;
import com.parse.*;

@ParseClassName("Announcement")
public class ParseAnnouncement extends ParseObject {
    public String getTitle() {
        System.out.println("Announcement Title:"+getString("title"));
        return getString("title");
    }

    public void setTitle(String value) {
        put("title", value);
    }

    public String getConent() {
        return getString("content");
    }

    public void setConent(String content) {
        put("content", content);
    }

    public String getPosttime() {
        return getCreatedAt().toString();
    }

    public static ParseQuery<ParseAnnouncement> getQuery() {
        return ParseQuery.getQuery(ParseAnnouncement.class);
    }
}