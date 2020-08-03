package com.example.androiddevelopment_kennypassenier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainListViewFragment extends Fragment {


    private String[] mMenuItems;
    private ListView mListView;

    public MainListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        // Todo populate list with database items
        mMenuItems = new String[]{
                "First item",
                "Second item",
                "Third item",
                "Etc"
        };

        mListView = view.findViewById(R.id.frgmt_list);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mMenuItems
        );

        mListView.setAdapter(listViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra("course_position", position);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}