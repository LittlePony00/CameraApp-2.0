package com.example.cameraapp20.CustomAdapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.example.cameraapp20.DB.Photos;
import com.example.cameraapp20.R;

import java.util.List;

public class BaseAdapter extends android.widget.BaseAdapter {

    List<Photos> photosList;
    Context context;
    GridViewInterface gridViewInterface;
    private int x = -1;


    public BaseAdapter(Context context) {
        this.context = context;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (photosList == null)
            return 0;

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

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.camera_item, null);
        }

        convertView.setOnLongClickListener(view -> {
            gridViewInterface.onLongItemClick(position);
            if (x == position){
                x = -1;
                notifyDataSetChanged();
            }else {
                x = position;
            }

            return true;
        });

        SharedPreferences sharedPreferences = convertView.getContext().getSharedPreferences("MODE", MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("night", false);
        CardView cardView = convertView.findViewById(R.id.cardView);

        if (nightMode) {
            cardView.setCardBackgroundColor(convertView.getContext().getColor(R.color.yellow));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            cardView.setCardBackgroundColor(convertView.getContext().getColor(R.color.purple));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        ImageView imageView = convertView.findViewById(R.id.CardImage);
        TextView textView = convertView.findViewById(R.id.CardText);

        imageView.setImageURI(Uri.parse(photosList.get(position).path));
        textView.setText(photosList.get(position).result);

        return convertView;
    }
}

