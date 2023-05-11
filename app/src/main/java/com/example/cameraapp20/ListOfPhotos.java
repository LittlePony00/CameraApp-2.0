package com.example.cameraapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.cameraapp20.CustomAdapter.BaseAdapter;
import com.example.cameraapp20.DB.Photos;
import com.example.cameraapp20.DB.PhotosDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListOfPhotos extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    PhotosDB db;
    List<Photos> photosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_photos);
        db = PhotosDB.getInstance(this.getApplicationContext());
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_list);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }else if (item.getItemId() == R.id.bottom_gallery){
                startActivity(new Intent(getApplicationContext(), Settings.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }else {
                return true;
            }
            return false;
        });

        init_adapter();
    }


    private void init_adapter() {
        GridView gridView = findViewById(R.id.grid_view);
        BaseAdapter baseAdapter = new BaseAdapter(this.getApplicationContext());

        Photos photos1 = new Photos();
        photos1.path = "";
        photos1.result = "KDLsdkaldlada";
        Photos photos2 = new Photos();
        photos1.path = "";
        photos1.result = "KDLsdkaldlada";
        db.photosDAO().insert(photos1);
        db.photosDAO().insert(photos2);

        photosList = db.photosDAO().getAllPhotos();
        baseAdapter.setPhotosList(photosList);
        gridView.setAdapter(baseAdapter);
    }
}