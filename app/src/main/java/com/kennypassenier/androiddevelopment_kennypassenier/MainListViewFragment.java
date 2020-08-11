package com.kennypassenier.androiddevelopment_kennypassenier;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kennypassenier.androiddevelopment_kennypassenier.models.GetAllMoviesDelegate;
import com.kennypassenier.androiddevelopment_kennypassenier.models.Movie;
import com.kennypassenier.androiddevelopment_kennypassenier.models.MovieDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainListViewFragment extends Fragment implements GetAllMoviesDelegate{



    private ListView mListView;
    private List<Movie> mMovies;


    public MainListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        // Instantieer een lege lijst om null reference te voorkomen
        mMovies = new ArrayList<Movie>();
        // Start een async task
        updateMovies();
        // Zet de listview als member variabele
        mListView = view.findViewById(R.id.frgmt_list);
        // Stel de adapter in
        updateAdapter();
        // Event listener voor als er op een item uit de lijst geklikt wordt
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Start een intent met het id van de film als parameter voor de nieuwe activity
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("movie_id", position + 1);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void updateAdapter() {
        ArrayAdapter listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mMovies
        );

        mListView.setAdapter(listViewAdapter);
    }

    // Call naar asyncTask zodat we de UI thread vrijwaren van een zware taak.
    private void updateMovies(){
        new GetAllMoviesAsyncTask(this).execute();
    }

    @Override
    public void onMoviesRetrieved(List<Movie> movies) {
        mMovies = movies;
        // Ik had eerst geprobeerd om mListViewAdapter.notifyDataSetChanged(); te gebruiken, maar dat wilde niet werken
        // Daarmee deze call naar updateAdapter
        updateAdapter();
    }

    public static class GetAllMoviesAsyncTask extends AsyncTask<List<Movie>, Void, List<Movie>>{
        private final MovieDatabase db;
        private final GetAllMoviesDelegate mGetAllMoviesDelegate;

        public GetAllMoviesAsyncTask(GetAllMoviesDelegate delegate) {
            this.db = MainActivity.mMovieDatabase;
            this.mGetAllMoviesDelegate = delegate;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            mGetAllMoviesDelegate.onMoviesRetrieved(movies);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected List<Movie> doInBackground(List<Movie>... lists) {
            return db.movieDAO().getAll();
        }
    }


}