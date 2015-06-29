package com.osapps.stepapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.BitmapFactory;

public final class PersonEntryAdapter extends ArrayAdapter<Person> {

    private final int personItemLayoutResource;

    public PersonEntryAdapter(final Context context, final int personItemLayoutResource) {
        //Specify the Context to our parent class, ArrayAdapter,
        super(context, 0);
        //THe personItemLayoutResource tells us which layout we should use for each person item
        this.personItemLayoutResource = personItemLayoutResource;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        // We need to get the best view (re-used if possible) and then
        // retrieve its corresponding ViewHolder, which optimizes lookup efficiency
        final View view = getWorkingView(convertView);
        final ViewHolder viewHolder = getViewHolder(view);
        final Person entry = getItem(position);

        // Setting the title view is straightforward
        //System.out.println("Text should display: "+entry.personName);
        viewHolder.nameTextView.setText(entry.name);
        viewHolder.positionTextView.setText(entry.role);
        viewHolder.imageView.setImageBitmap(BitmapFactory.decodeResource(view.getResources(), entry.getImageID()));



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

            workingView = inflater.inflate(personItemLayoutResource, null);
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

            viewHolder.nameTextView = (TextView) workingView.findViewById(R.id.peopleNameLabel);
            viewHolder.positionTextView = (TextView) workingView.findViewById(R.id.peoplePositionLabel);
            viewHolder.imageView = (ImageView) workingView.findViewById(R.id.peopleImageView);

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
        public ImageView imageView;
        public TextView nameTextView;
        public TextView positionTextView;
    }


}
