package com.example.androiddevelopment_kennypassenier;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;

public class AddMovieFragment extends Fragment {


    private EditText mAddNewMovieText;
    private Button mAddNewMovieButton;

    public AddMovieFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_movie, container, false);

        mAddNewMovieButton = view.findViewById(R.id.addNewMovieButton);
        mAddNewMovieText = view.findViewById(R.id.txtNewMovieName);

        mAddNewMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Only do something if mAddNewMovieText has some information
                if(!mAddNewMovieText.getText().toString().isEmpty()){
                    // Send request to omdb and check the result.

                    String dataUrl = "https://www.omdbapi.com/?i=tt0944947&plot=full&apikey=48ba3731";
                    //String testData = OMDBDataSingleton.getInstance().downloadPlainText(dataUrl);

                    // todo once we have the json data, we can insert the data into our database
                    // Add a movie
                    Movie userMovie = new Movie();
                    userMovie.setDirector("Ridley Scott");
                    userMovie.setTitle("Prometheus");
                    userMovie.setPlot("Following clues to the origin of mankind, a team finds a structure on a distant moon, but they soon realize they are not alone.\n" +
                            "\n");
                    userMovie.setReleaseDate(2012);
                    userMovie.setPosterUrl("https://m.media-amazon.com/images/M/MV5BMTY3NzIyNTA2NV5BMl5BanBnXkFtZTcwNzE2NjI4Nw@@._V1_SY264_CR0,0,178,264_AL_.jpg");

                    new AddSingleMovieAsyncTask().execute(userMovie);

                    // Todo remove
                    //MainActivity.mMovieDatabase.movieDAO().insert(userMovie);
                    //mActivityCallback.addMovie(userMovie);
                    Log.d("ADDMOVIE", "onClick: Adding movie");
                    Toast.makeText(getContext(), R.string.movieAddedText, Toast.LENGTH_SHORT);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        return view;
    }

    public class AddSingleMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDatabase db;


        public AddSingleMovieAsyncTask() {
            this.db = MainActivity.mMovieDatabase;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            db.movieDAO().insert(movies[0]);
            return null;
        }
    }


}