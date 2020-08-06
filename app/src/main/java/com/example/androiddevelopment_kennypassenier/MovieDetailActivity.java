package com.example.androiddevelopment_kennypassenier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.androiddevelopment_kennypassenier.models.GetSingleMoviesDelegate;
import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;

public class MovieDetailActivity extends AppCompatActivity implements GetSingleMoviesDelegate, DarkModeSwitchFragment.DarkModeSwitchListener {


    private ConstraintLayout mMainLayout;
    private MovieDetailTextInfoFragment mMovieDetailTextInfoFragment;
    private DarkModeSwitchFragment mDarkModeSwitchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Referentie naar onze layout file zodat we de achtergrond kunnen veranderen bij DarkModeSwitch
        mMainLayout = findViewById(R.id.activity_movie_detail);
        // Referentie naar de fragments die we gebruiken in deze activity
        mMovieDetailTextInfoFragment = (MovieDetailTextInfoFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail_text_info_fragment);
        mDarkModeSwitchFragment = (DarkModeSwitchFragment) getSupportFragmentManager().findFragmentById(R.id.dark_mode_switch_fragment);

        int listPosition = getIntent().getIntExtra("movie_id", -1);

        Log.d("test", "onCreate: Position?" + listPosition);

        // If the default value is -1, something has gone wrong
        if(listPosition != -1){
            getMovie(listPosition);
        }
        else{
            Log.d("MOVIEDETAILACTIVITY", "onCreate: Wrong list position");
        }

    }

    @Override
    public void onMovieRetrieved(Movie movie) {
        if(mMovieDetailTextInfoFragment != null){
            // Zet al dfe informatie van de film in de TextViews van onze fragment
            mMovieDetailTextInfoFragment.setTitleText(movie.getTitle());
            mMovieDetailTextInfoFragment.setDirectorText(movie.getDirector());
            mMovieDetailTextInfoFragment.setPlotText(movie.getPlot());
            mMovieDetailTextInfoFragment.setReleaseDateText(movie.getReleaseDate().toString());
        }
    }

    private void getMovie(int position){
        new GetSingleMoviesAsyncTask(this).execute(position);
    }

    @Override
    public void darkModeToggle(boolean isDarkMode) {
        if(isDarkMode){
            mMainLayout.setBackgroundColor(Color.BLACK);
        }
        else{
            mMainLayout.setBackgroundColor(Color.WHITE);

        }
        mMovieDetailTextInfoFragment.setDarkMode(isDarkMode);
    }


    public class GetSingleMoviesAsyncTask extends AsyncTask<Integer, Void, Movie> {
        private MovieDatabase db;
        private GetSingleMoviesDelegate mGetSingleMoviesDelegate;

        public GetSingleMoviesAsyncTask(GetSingleMoviesDelegate delegate) {
            this.db = MainActivity.mMovieDatabase;
            this.mGetSingleMoviesDelegate = delegate;
        }

        @Override
        protected void onPostExecute(Movie movie) {
            super.onPostExecute(movie);
            mGetSingleMoviesDelegate.onMovieRetrieved(movie);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Movie doInBackground(Integer... integers) {
            return db.movieDAO().getMovie(integers[0]);
        }
    }


}