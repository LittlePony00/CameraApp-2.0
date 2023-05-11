package com.example.cameraapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cameraapp20.CustomAdapter.BaseAdapter;
import com.example.cameraapp20.CustomAdapter.GridViewInterface;
import com.example.cameraapp20.DB.Photos;
import com.example.cameraapp20.DB.PhotosDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListOfPhotos extends AppCompatActivity implements GridViewInterface {
    BottomNavigationView bottomNavigationView;
    PhotosDB db;
    GridView gridView;
    BaseAdapter baseAdapter;
    List<Photos> photosList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    boolean nightMode;

    @SuppressLint("MissingInflatedId")
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
                if (photosList.size() != 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        try {
                            //Toast.makeText(this, "File was also deleted", Toast.LENGTH_LONG).show();
                            Files.deleteIfExists(
                                    Paths.get(photosList.get(0).path));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        db.photosDAO().delete(photosList.get(0));
                        photosList = db.photosDAO().getAllPhotos();
                        baseAdapter.setPhotosList(photosList);
                    }
                }
            }
            return false;
        });

        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        init_adapter();
    }


    @SuppressLint("ResourceAsColor")
    private void init_adapter() {
        gridView = findViewById(R.id.grid_view);
        baseAdapter = new BaseAdapter(this.getApplicationContext());
        photosList = db.photosDAO().getAllPhotos();
        if (photosList.size() != 0) {
            gridView.setAdapter(baseAdapter);
            baseAdapter.setPhotosList(photosList);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {
        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
        photosList = db.photosDAO().getAllPhotos();
        if (photosList.size() != 0) {
            db.photosDAO().delete(photosList.get(position));
            baseAdapter.notifyDataSetChanged();
        }
    }
}