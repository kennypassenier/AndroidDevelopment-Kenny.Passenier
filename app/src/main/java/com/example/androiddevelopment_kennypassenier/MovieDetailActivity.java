package com.example.androiddevelopment_kennypassenier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androiddevelopment_kennypassenier.models.GetAllMoviesDelegate;
import com.example.androiddevelopment_kennypassenier.models.GetSingleMoviesDelegate;
import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;

public class MovieDetailActivity extends AppCompatActivity implements GetSingleMoviesDelegate {

    private TextView mTitle;
    private EditText mBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mTitle = findViewById(R.id.txtTitle);
        mBody = findViewById(R.id.txtBody);


        int listPosition = getIntent().getIntExtra("movie_id", -1);

        Log.d("test", "onCreate: Position?" + listPosition);

        // If the default value is -1, something has gone wrong
        if(listPosition != -1){
            getMovie(listPosition);
            mTitle.setText("Loading");
            mBody.setText("Loading");
        }
        else{
            Log.d("MOVIEDETAILACTIVITY", "onCreate: Wrong list position");
        }

    }

    @Override
    public void onMovieRetrieved(Movie movie) {
            mTitle.setText(movie.getTitle());
            mBody.setText(movie.getPlot());

    }

    private void getMovie(int position){
        new GetSingleMoviesAsyncTask(this).execute(position);
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