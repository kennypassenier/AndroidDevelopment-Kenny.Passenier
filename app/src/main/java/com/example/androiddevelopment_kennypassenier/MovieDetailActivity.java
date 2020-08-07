package com.example.androiddevelopment_kennypassenier;

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
import android.util.Log;

import com.example.androiddevelopment_kennypassenier.models.FetchImageService;
import com.example.androiddevelopment_kennypassenier.models.GetSingleMoviesDelegate;
import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;

public class MovieDetailActivity extends AppCompatActivity implements GetSingleMoviesDelegate, DarkModeSwitchFragment.DarkModeSwitchListener {


    private ConstraintLayout mMainLayout;
    private MovieDetailInfoFragment mMovieDetailInfoFragment;
    // todo maybe we don't need this as a member
    private DarkModeSwitchFragment mDarkModeSwitchFragment;
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
        mDarkModeSwitchFragment = (DarkModeSwitchFragment) getSupportFragmentManager().findFragmentById(R.id.dark_mode_switch_fragment);


        // Registreer de broadcastreceiver
        mImageBroadcastReceiver = new ImageBroadcastReceiver();
        // Maak intent aan waar we onze IntentService aan doorgeven
        mFetchImageService = new Intent(this, FetchImageService.class);
        // definieer intent filter
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(FetchImageService.NOTIFY_IMAGE);



        int movieId = getIntent().getIntExtra("movie_id", -1);

        Log.d("test", "onCreate: Position?" + movieId);

        // If the default value is -1, something has gone wrong
        if(movieId != -1){
            getMovie(movieId);
        }
        else{
            Log.d("MOVIEDETAILACTIVITY", "onCreate: Wrong list position");
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

    private class ImageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // todo remove
            int test = 0;
            // De actie uit de intent halen, in ons geval is er momenteel maar 1 actie
            String action = intent.getAction();
            if(action != null && action == FetchImageService.NOTIFY_IMAGE){
                // Bitmap uit de intent halen
                Bitmap posterImage = (Bitmap) intent.getParcelableExtra("posterImage");
                mMovieDetailInfoFragment.setPosterImage(posterImage);
            }
        }
    }


}