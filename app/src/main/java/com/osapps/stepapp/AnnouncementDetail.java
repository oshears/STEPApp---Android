package com.osapps.stepapp;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import com.parse.*;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class AnnouncementDetail extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_detail);

        final String announcementTitle = getIntent().getStringExtra("announcementTitle");
        final String announcementDate = getIntent().getStringExtra("announcementDate");
        final String announcementContent = getIntent().getStringExtra("announcementContent");

        //Find the text fields
        final TextView announcementTitleLabel = (TextView) findViewById(R.id.detial_announcement_title);
        announcementTitleLabel.setText(announcementTitle);
        final TextView announcementDateLabel = (TextView) findViewById(R.id.detial_announcement_date);
        announcementDateLabel.setText(announcementDate);
        final TextView announcementContentLabel = (TextView) findViewById(R.id.detial_announcement_content);
        announcementContentLabel.setText(announcementContent);

    }
}
