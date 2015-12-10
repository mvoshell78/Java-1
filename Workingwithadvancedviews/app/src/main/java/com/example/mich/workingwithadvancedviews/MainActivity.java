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

    //set up var for my list and spinner
    private ListView mListView;
    private Spinner mSpinner;

    //needed in the adapter to make it work
    long lineNumber = 0;


    // setsup the hashmap
    ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String,String>>();



    // simple adapter
    private void setSimpleAdapter(){

        // var to hold the text in the list/spinner
        final String name = "name";
        final String jobRes = "department";
        final String phone = "phone";
        final String bio = "bio";





        // for loop to fill the array
        for (Workers employee : mWorkers) {

            HashMap<String, String> map = new HashMap<String, String>();

            map.put(name, employee.getName());
            map.put(jobRes, employee.getJobRes());
            map.put(phone, employee.getPhone());
            map.put(bio, employee.getBio());

            elements.add(map);
        }

        // Creating an array of our keys
        String[] keys = new String[]{
                name, jobRes, phone, bio,
        };

        // ties the xml file to the spinner/list
        int[] views = new int[]{
                R.id.employee_name,
                R.id.employee_department,

                // if you wanted the phone in the list or spinner uncomment the following code
                // R.id.employee_phone,

        };

        // var to figure out the screen rotation
        final int screenOrientation = getResources().getConfiguration().orientation;

        //if to execute pased on orientation
        //2 equals landscape
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



        // creates a new arrayList called mWorkers
        mWorkers = new ArrayList<>();

        // adds the items to the list
        mWorkers.add(new Workers("Bev Macdonald", "Loader","(302) 332-5631", "Bev is 34 years old and a mother of 6. She has been with our comaont for 8 years and is a very effective worker with a bright future."));
        mWorkers.add(new Workers("Dana Steele", "Order Picking","(302) 241-5638", "Dana is 27 and is our part time worker. She currently enjoys knitting and skydiving simultaniouly. she has been with us for 4 years and want to be full time"));
        mWorkers.add(new Workers("Eric Malascalza", "Fueling","(302) 734-2981", "Eric is 29 and has been with us for over 10 years. he is currently our senior MH and wants to some day be all grown up and run the place."));
        mWorkers.add(new Workers("Mich Voshell", "Manager","(302) 653-2332", "Mich is the baddest mojang in town he ahs been with us longer than the founders. Some day he may run the joint."));

        final int screenOrientation = getResources().getConfiguration().orientation;

        if (screenOrientation == 2) {


            mListView = (ListView) findViewById(R.id.listView);

            setSimpleAdapter();

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    lineNumber = mListView.getItemIdAtPosition(i);
                    populateUI();


                }
            });


        } else {

            mSpinner = (Spinner) findViewById(R.id.spinner);

            setSimpleAdapter();

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    lineNumber = mSpinner.getItemIdAtPosition(i);
                    populateUI();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }


    }
    // shared ui population method
    private void populateUI() {
        int newPos = (int) lineNumber;

        TextView name = (TextView) findViewById(R.id.WorkersName);
        TextView jobRes = (TextView) findViewById(R.id.jobRespon);
        TextView phone = (TextView) findViewById(R.id.WorkersPhone);
        TextView bio = (TextView) findViewById(R.id.WorkersBio);

        String mName = mWorkers.get(newPos).getName();
        String mJobRes = mWorkers.get(newPos).getJobRes();
        String mPhone = mWorkers.get(newPos).getPhone();
        String mBio = mWorkers.get(newPos).getBio();

        name.setText(mName);
        bio.setText(mBio);
        jobRes.setText(mJobRes);
        phone.setText(mPhone);
    }


}
