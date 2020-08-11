package com.kennypassenier.androiddevelopment_kennypassenier.models;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    @org.junit.jupiter.api.Test
    void testToString() {
        Movie movie = new Movie();
        movie.setTitle("movieTitle");
        movie.setReleaseDate(2020);
        assertEquals("movieTitle - 2020", movie.toString());
    }

    @org.junit.jupiter.api.Test
    void getTitle() {
        Movie movie = new Movie();
        movie.setTitle("TestMovie");
        assertEquals("TestMovie", movie.getTitle());
    }
}