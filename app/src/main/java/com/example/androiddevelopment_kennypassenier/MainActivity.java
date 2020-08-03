package com.example.androiddevelopment_kennypassenier;

import android.os.Bundle;

import com.example.androiddevelopment_kennypassenier.ui.main.Movie;
import com.example.androiddevelopment_kennypassenier.ui.main.MovieDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.androiddevelopment_kennypassenier.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements AddMovieFragment.AddMovieListener {


    public static MovieDatabase movieDatabase;


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
        movieDatabase = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "moviedb").allowMainThreadQueries().build();



    }

    @Override
    public void addMovie(Movie movie) {
        movieDatabase.movieDAO().insert(movie);

    }
}