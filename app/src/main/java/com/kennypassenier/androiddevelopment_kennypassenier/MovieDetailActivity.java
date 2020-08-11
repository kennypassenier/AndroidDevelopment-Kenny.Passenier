package com.kennypassenier.androiddevelopment_kennypassenier;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.kennypassenier.androiddevelopment_kennypassenier.models.FetchImageService;
import com.kennypassenier.androiddevelopment_kennypassenier.models.GetSingleMoviesDelegate;
import com.kennypassenier.androiddevelopment_kennypassenier.models.Movie;
import com.kennypassenier.androiddevelopment_kennypassenier.models.MovieDatabase;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MovieDetailActivity extends AppCompatActivity implements GetSingleMoviesDelegate, DarkModeSwitchFragment.DarkModeSwitchListener {


    private ConstraintLayout mMainLayout;
    private MovieDetailInfoFragment mMovieDetailInfoFragment;
    private Intent mFetchImageService;
    private ImageBroadcastReceiver mImageBroadcastReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        // Referentie naar onze layout file zodat we de achtergrond kunnen veranderen bij DarkModeSwitch
        mMainLayout = findViewById(R.id.activity_movie_detail);
        // Referentie naar de fragments die we gebruiken in deze activity
        mMovieDetailInfoFragment = (MovieDetailInfoFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail_text_info_fragment);
        // Registreer de broadcastreceiver
        mImageBroadcastReceiver = new ImageBroadcastReceiver();
        // Maak intent aan waar we onze IntentService aan doorgeven
        mFetchImageService = new Intent(this, FetchImageService.class);
        // definieer intent filter
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(FetchImageService.NOTIFY_IMAGE);
        int movieId = getIntent().getIntExtra("movie_id", -1);
        // If the default value is -1, something has gone wrong
        if(movieId != -1){
            getMovie(movieId);
        }
        else{
            Snackbar snackbar = Snackbar.make(mMainLayout, R.string.movieIdException, BaseTransientBottomBar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Registreer onze receiver zolang we actief zijn
        LocalBroadcastManager.getInstance(this).registerReceiver(mImageBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Wanneer de activity niet actief is, luisteren we niet naar de broadcastreceiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mImageBroadcastReceiver);
    }

    @Override
    public void onMovieRetrieved(Movie movie) {
        if(mMovieDetailInfoFragment != null){
            // Zet al dfe informatie van de film in de TextViews van onze fragment
            mMovieDetailInfoFragment.setTitleText(movie.getTitle());
            mMovieDetailInfoFragment.setDirectorText(movie.getDirector());
            mMovieDetailInfoFragment.setPlotText(movie.getPlot());
            mMovieDetailInfoFragment.setReleaseDateText(movie.getReleaseDate().toString());

            // Start de IntentService waarmee we de imageView gaan opvullen
            mFetchImageService.putExtra("posterUrl", movie.getPosterUrl());
            startService(mFetchImageService);
        }
    }


    private void getMovie(int id){
        new GetSingleMoviesAsyncTask(this).execute(id);
    }

    @Override
    public void darkModeToggle(boolean isDarkMode) {
        if(isDarkMode){
            mMainLayout.setBackgroundColor(Color.BLACK);
        }
        else{
            mMainLayout.setBackgroundColor(Color.WHITE);

        }
        mMovieDetailInfoFragment.setDarkMode(isDarkMode);
    }


    public static class GetSingleMoviesAsyncTask extends AsyncTask<Integer, Void, Movie> {
        private final MovieDatabase db;
        private final GetSingleMoviesDelegate mGetSingleMoviesDelegate;

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

    private class ImageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // De actie uit de intent halen, in ons geval is er momenteel maar 1 actie
            String action = intent.getAction();
            if(action == FetchImageService.NOTIFY_IMAGE){
                // Bitmap uit de intent halen
                Bitmap posterImage = (Bitmap) intent.getParcelableExtra("posterImage");
                mMovieDetailInfoFragment.setPosterImage(posterImage);
            }
        }
    }
}