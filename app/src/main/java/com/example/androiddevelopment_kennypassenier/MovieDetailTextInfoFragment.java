package com.example.androiddevelopment_kennypassenier;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MovieDetailTextInfoFragment extends Fragment {

    private TextView mTitle;
    private TextView mPlot;
    private TextView mReleaseDate;
    private TextView mDirector;
    private TextView mMPlotLable;
    private TextView mMReleaseDateLable;
    private TextView mMDirectorLable;
    private TextView mMTitleLable;

    private ImageView mImgPlot;

    public MovieDetailTextInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_detail_text_info, container, false);
        // Zet TextViews als members
        mTitle = view.findViewById(R.id.txtTitle);
        mPlot = view.findViewById(R.id.txtPlot);
        mDirector = view.findViewById(R.id.txtDirector);
        mReleaseDate = view.findViewById(R.id.txtReleaseDate);
        mImgPlot = view.findViewById(R.id.imgPlot);
        // Hetzelfde voor de labels
        mMTitleLable = view.findViewById(R.id.txtLabelTitle);
        mMDirectorLable = view.findViewById(R.id.txtLabelDirector);
        mMReleaseDateLable = view.findViewById(R.id.txtLabelReleaseDate);
        mMPlotLable = view.findViewById(R.id.txtLabelPlot);
        return view;
    }


    public void setTitleText(String info){
        mTitle.setText(info);
    }
    public void setDirectorText(String info){
        mDirector.setText(info);
    }
    public void setReleaseDateText(String info){
        mReleaseDate.setText(info);
    }
    public void setPlotText(String info){
        mPlot.setText(info);
    }




    public void setDarkMode(boolean isDarkMode) {
        if(isDarkMode){
            mTitle.setTextColor(Color.WHITE);
            mDirector.setTextColor(Color.WHITE);
            mReleaseDate.setTextColor(Color.WHITE);
            mPlot.setTextColor(Color.WHITE);
            mMDirectorLable.setTextColor(Color.WHITE);
            mMPlotLable.setTextColor(Color.WHITE);
            mMReleaseDateLable.setTextColor(Color.WHITE);
            mMTitleLable.setTextColor(Color.WHITE);
        }
        else{
            mTitle.setTextColor(Color.BLACK);
            mDirector.setTextColor(Color.BLACK);
            mReleaseDate.setTextColor(Color.BLACK);
            mPlot.setTextColor(Color.BLACK);
            mMDirectorLable.setTextColor(Color.BLACK);
            mMPlotLable.setTextColor(Color.BLACK);
            mMReleaseDateLable.setTextColor(Color.BLACK);
            mMTitleLable.setTextColor(Color.BLACK);
        }
    }
}