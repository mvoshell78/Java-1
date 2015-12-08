package com.example.mich.workingwithadvancedviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Workers> mWorkers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView testView = (TextView) findViewById(R.id.textView);

        mWorkers = new ArrayList<>();
        mWorkers.add(new Workers("Bev Macdonald", "Loading"));
        mWorkers.add(new Workers("Dana Steele", "Order Picking"));
        mWorkers.add(new Workers("Eric Malascalza", "Fueling"));


        if (!(mWorkers.isEmpty())){
            testView.setText("Array List is loaded");
        }
    }
}
