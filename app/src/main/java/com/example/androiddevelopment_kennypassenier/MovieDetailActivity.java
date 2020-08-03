package com.example.androiddevelopment_kennypassenier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView mTitle;
    private EditText mBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mTitle = findViewById(R.id.txtTitle);
        mBody = findViewById(R.id.txtBody);

        int listPosition = getIntent().getIntExtra("course_position", -1);

        // If the default value is -1, something has gone wrong
        if(listPosition != -1){

        }

        mTitle.setText("Position: " + listPosition);
        mBody.setText("This is the standard body that is shown when we don't have anything to actually put into this\n We should really fill this dynamically");
    }
}