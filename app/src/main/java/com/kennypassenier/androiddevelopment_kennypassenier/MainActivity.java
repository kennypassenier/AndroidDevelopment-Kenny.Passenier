package com.kennypassenier.androiddevelopment_kennypassenier;

import android.os.Bundle;

import com.kennypassenier.androiddevelopment_kennypassenier.models.MovieDatabase;
import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.kennypassenier.androiddevelopment_kennypassenier.ui.main.SectionsPagerAdapter;

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
        mMovieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "moviedb").build();
    }
}