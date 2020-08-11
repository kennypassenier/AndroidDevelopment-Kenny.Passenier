package com.example.androiddevelopment_kennypassenier.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAll();

    @Query("SELECT * from movies where id= :id")
    Movie getMovie(int id);
}
