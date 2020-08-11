package com.example.androiddevelopment_kennypassenier.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDAO {
    @Insert
    void insert(Movie movie);
    @Update
    void update(Movie movie);

    @Delete
    int delete(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAll();

    @Query("SELECT * from movies where id= :id")
    Movie getMovie(int id);
}
