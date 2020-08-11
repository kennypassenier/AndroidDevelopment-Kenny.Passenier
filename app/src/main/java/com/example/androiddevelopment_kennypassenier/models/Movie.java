package com.example.androiddevelopment_kennypassenier.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// We veranderen de naam van de tabel naar het meervoud
@Entity(tableName = "movies")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "plot")
    private String plot;
    @ColumnInfo(name = "releaseDate")
    private Integer releaseDate;
    @ColumnInfo(name = "posterUrl")
    private String posterUrl;
    public Movie(){

    }
    @Override
    public String toString(){
        return title + " - " + releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
