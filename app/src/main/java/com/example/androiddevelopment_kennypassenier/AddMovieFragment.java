package com.example.androiddevelopment_kennypassenier;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androiddevelopment_kennypassenier.models.AddMovieDelegate;
import com.example.androiddevelopment_kennypassenier.models.Movie;
import com.example.androiddevelopment_kennypassenier.models.MovieDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class AddMovieFragment extends Fragment implements AddMovieDelegate {


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





                    // Instantiate the RequestQueue.
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    String url = String.format("https://www.omdbapi.com/?t=%s&plot=full&apikey=48ba3731", mAddNewMovieText.getText().toString());

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    // Correcte response

                                    try {
                                        // Response omzetten naar JSON
                                        JSONObject jsonObject = new JSONObject(response);
                                        String title = jsonObject.getString("Title");
                                        String plot = jsonObject.getString("Plot");
                                        String director = jsonObject.getString("Director");
                                        Integer releaseDate = jsonObject.getInt("Year");
                                        String posterUrl = jsonObject.getString("Poster");
                                        // JSON gebruiken om nieuwe film gegevens in te vullen
                                        Movie newMovie = new Movie();
                                        newMovie.setTitle(title);
                                        newMovie.setPlot(plot);
                                        newMovie.setDirector(director);
                                        newMovie.setReleaseDate(releaseDate);
                                        newMovie.setPosterUrl(posterUrl);
                                        // Nieuwe film toevoegen aan de database
                                        new AddSingleMovieAsyncTask(AddMovieFragment.this).execute(newMovie);


                                    } catch (JSONException e) {

                                        // Todo iets met deze error doen, foute responses afvangen
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mAddNewMovieText.setText(error.toString());
                        }
                    });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                }
            }
        });

        return view;
    }

    @Override
    public void onMovieAdded() {
        // We sturen de intent enkel als hij succesvol is opgeslagen in onze database
        // Anders kan het gebeuren dat deze nieuwe film nog niet voorkomt in onze lijst
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    public class AddSingleMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDatabase db;
        private AddMovieDelegate mAddMovieDelegate;

        public AddSingleMovieAsyncTask(AddMovieDelegate delegate) {
            this.mAddMovieDelegate = delegate;
            this.db = MainActivity.mMovieDatabase;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            db.movieDAO().insert(movies[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAddMovieDelegate.onMovieAdded();
        }
    }


}