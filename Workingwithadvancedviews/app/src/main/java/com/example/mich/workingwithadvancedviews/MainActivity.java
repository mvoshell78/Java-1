// Michael Voshell
//week 3 project



package com.example.mich.workingwithadvancedviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    // sets up an arrayList of the workers class
    private ArrayList<Workers> mWorkers;
    private ListView mListView;
    private Spinner mSpinner;
    long position = 0;

    ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String,String>>();
    private void setSimpleAdapter(){

        // Field identifiers
        final String name = "name";
        final String department = "department";
        //final String years = "years";

        // List of elements for our adapter.


        // Goes through each employee and maps the data elements to a String key.
        for (Workers employee : mWorkers) {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put(name, employee.getName());
            map.put(department, employee.getDepartment());

            elements.add(map);
        }

        // Creating an array of our keys
        String[] keys = new String[]{
                name, department
        };

        // Creating an array of our list item components.
        // Indices must match the keys array.
        int[] views = new int[]{
                R.id.employee_name,
                R.id.employee_department,

        };
        final int screenOrientation = getResources().getConfiguration().orientation;

        if (screenOrientation == 2){
            // Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
            SimpleAdapter adapter = new SimpleAdapter(this, elements, R.layout.listitem, keys, views);

            mListView.setAdapter(adapter);
        } else {
            // Creating a new SimpleAdapter that maps values to views using our keys and views arrays.
            SimpleAdapter adapter = new SimpleAdapter(this, elements, R.layout.listitem, keys, views);

            mSpinner.setAdapter(adapter);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView testView = (TextView) findViewById(R.id.testView);
        final TextView portTestView = (TextView) findViewById(R.id.portTestView);
        // creates a new arrayList called mWorkers
        mWorkers = new ArrayList<>();

        // adds the items to the list
        mWorkers.add(new Workers("Bev Macdonald", "Loading"));
        mWorkers.add(new Workers("Dana Steele", "Order Picking"));
        mWorkers.add(new Workers("Eric Malascalza", "Fueling"));
        mWorkers.add(new Workers("Mich Voshell", "Manager"));

        final int screenOrientation = getResources().getConfiguration().orientation;
        if (screenOrientation == 2) {


            mListView = (ListView) findViewById(R.id.listView);

            setSimpleAdapter();

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                   position = mListView.getItemIdAtPosition(i);
                    int newPos = (int) position;


                    testView.setText(new StringBuilder().append("clicked ").append(newPos).toString());

                }
            });


        } else {

            mSpinner = (Spinner) findViewById(R.id.spinner);

            setSimpleAdapter();

            portTestView.setText("this is regestering in portrait");
        }


    }



}
