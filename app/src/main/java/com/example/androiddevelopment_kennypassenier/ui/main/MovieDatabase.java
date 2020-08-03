package com.example.androiddevelopment_kennypassenier.ui.main;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDAO movieDAO();

}
