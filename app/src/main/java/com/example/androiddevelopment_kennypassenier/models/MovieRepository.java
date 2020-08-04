package com.example.androiddevelopment_kennypassenier.models;

import android.content.Context;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MovieRepository {

    private static MovieRepository instance;
    private static ExecutorService mExecutorService;
    private MovieDatabase mMovieDatabase;


    private MovieRepository(Context context){
        mMovieDatabase = Room.databaseBuilder(context, MovieDatabase.class, "movies").build();
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static MovieRepository getInstance(Context context){
        if(instance == null){
            instance = new MovieRepository(context);
        }
        return instance;
    }

    public Movie getMovieById(int id){
        return mMovieDatabase.movieDAO().getMovie(id);
    }

    public List<Movie> getAllMovies(){
        return mMovieDatabase.movieDAO().getAll();
    }

    public void deleteMovie(Movie movie){
        mMovieDatabase.movieDAO().delete(movie);
    }

    public void updateMovie(Movie movie){
        mMovieDatabase.movieDAO().update(movie);
    }

    public void addMovie(Movie movie){
        mMovieDatabase.movieDAO().insert(movie);
    }


}
