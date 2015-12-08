// Michael Voshell
//week 3 project



package com.example.mich.workingwithadvancedviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    // sets up an arrayList of the workers class
    private ArrayList<Workers> mWorkers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView testView = (TextView) findViewById(R.id.textView);
        // creates a new arrayList called mWorkers
        mWorkers = new ArrayList<>();

        // adds the items to the list
        mWorkers.add(new Workers("Bev Macdonald", "Loading"));
        mWorkers.add(new Workers("Dana Steele", "Order Picking"));
        mWorkers.add(new Workers("Eric Malascalza", "Fueling"));

        // checks is the arraylist has contents
        if (!(mWorkers.isEmpty())){
            testView.setText("Array List is loaded");

        } else {
            testView.setText("Array List is empty");
        }
    }
}
