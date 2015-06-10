package com.osapps.stepapp;
import com.parse.*;

@ParseClassName("MiscAnnouncement")
public class ParseMiscAnnouncement extends ParseObject {

    public String getConent() {
        return getString("content");
    }

    public void setConent(String content) {
        put("content", content);
    }

    public String getPosttime() {
        return getCreatedAt().toString();
    }

    public static ParseQuery<ParseMiscAnnouncement> getQuery() {
        return ParseQuery.getQuery(ParseMiscAnnouncement.class);
    }
}