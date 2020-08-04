package com.example.androiddevelopment_kennypassenier;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;
import com.example.androiddevelopment_kennypassenier.models.MovieRepository;
import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddevelopment_kennypassenier.ui.main.SectionsPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static MovieDatabase mMovieDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Initialise and create the database
        // Todo stop allowing the database operations on the main thread
        mMovieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "moviedb").build();

    }




}