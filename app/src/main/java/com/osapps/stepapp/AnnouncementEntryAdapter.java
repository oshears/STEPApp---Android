package com.osapps.stepapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public final class AnnouncementEntryAdapter extends ArrayAdapter<Announcement> {

    private final int announcementItemLayoutResource;

    public AnnouncementEntryAdapter(final Context context, final int announcementItemLayoutResource) {
        //Specify the Context to our parent class, ArrayAdapter,
        super(context, 0);
        //THe announcementItemLayoutResource tells us which layout we should use for each announcement item
        this.announcementItemLayoutResource = announcementItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        // We need to get the best view (re-used if possible) and then
        // retrieve its corresponding ViewHolder, which optimizes lookup efficiency
        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        System.out.println("Position: "+position);
        final Announcement entry = getItem(position);
        System.out.println("Entry: "+entry);

        // Setting the title view is straightforward
        System.out.println("Text should display: "+entry.name);
        viewHolder.titleView.setText(entry.name);

        // Setting the subTitle view
        final String formattedSubTitle = String.format("Date: %s, Content: %s",
                entry.date,
                entry.content
        );
        System.out.println("This is a test");
        System.out.println("Text should display: "+formattedSubTitle);
        viewHolder.subTitleView.setText(formattedSubTitle);


        return view;
    }

    private View getWorkingView(final View convertView) {
        // The workingView is basically just the convertView re-used if possible
        // or inflated new if not possible
        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(announcementItemLayoutResource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {
        // The viewHolder allows us to avoid re-looking up view references
        // Since views are recycled, these references will never change
        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.titleView = (TextView) workingView.findViewById(R.id.annoncement_title);
            viewHolder.subTitleView = (TextView) workingView.findViewById(R.id.annoncement_date);

            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    /**
     * ViewHolder allows us to avoid re-looking up view references
     * Since views are recycled, these references will never change
     */
    private static class ViewHolder {
        public TextView titleView;
        public TextView subTitleView;
    }

}
