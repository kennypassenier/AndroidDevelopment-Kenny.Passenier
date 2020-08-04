package com.example.androiddevelopment_kennypassenier.models;

import java.util.List;

public interface GetAllMoviesDelegate{
    void onMoviesRetrieved(List<Movie> movies);
}
