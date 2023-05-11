package com.example.cameraapp20.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cameraapp20.DB.Photos;
import com.example.cameraapp20.R;

import java.util.List;

public class BaseAdapter extends android.widget.BaseAdapter {

    List<Photos> photosList;
    Context context;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photosList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.camera_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.CardImage);
        TextView textView = convertView.findViewById(R.id.CardText);

        return convertView;
    }
}

