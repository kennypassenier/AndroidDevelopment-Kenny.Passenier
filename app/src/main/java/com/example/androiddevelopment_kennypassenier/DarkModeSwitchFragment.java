package com.example.androiddevelopment_kennypassenier;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


public class DarkModeSwitchFragment extends Fragment {


    private DarkModeSwitchListener mActivityCallback;
    private Switch mDarkModeSwitch;

    public DarkModeSwitchFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_dark_mode_switch, container, false);
        // Zet switch als member field
        mDarkModeSwitch = view.findViewById(R.id.switchDarkMode);
        // Event listener voor switch
        mDarkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(mActivityCallback != null){
                    mActivityCallback.darkModeToggle(isChecked);
                    setDarkMode(isChecked);
                }
            }
        });


        return view;

    }

    // Link naar de activity die de DarkModeSwitchListener interface implementeert
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivityCallback = (DarkModeSwitchListener) context;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mActivityCallback = null;
    }

    public void setDarkMode(boolean isDarkMode) {
        if(isDarkMode){
            mDarkModeSwitch.setTextColor(Color.WHITE);
        }
        else{
            mDarkModeSwitch.setTextColor(Color.BLACK);
        }
    }


    public interface DarkModeSwitchListener{
        void darkModeToggle(boolean isDarkMode);
    }


}