package com.example.androiddevelopment_kennypassenier.ui.main;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert
    void insert(Movie movie);
    @Update
    void update(Movie movie);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAndReplace(Movie movie1, Movie movie2);
    // returns numberof deletedrows
    @Delete
    int delete(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAll();

    @Query("DELETE FROM movies")
    void deleteAll();

    @Query("DELETE FROM movies WHERE id= :id")
    void deleteById(int id);

    //if data needs to be synchronized with UI
    @Query("SELECT * FROM movies ORDER BY title ASC")
    LiveData<List<Movie>> getAllOrderedByName();

}
