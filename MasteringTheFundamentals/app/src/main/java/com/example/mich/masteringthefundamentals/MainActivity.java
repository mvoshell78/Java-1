package com.example.mich.masteringthefundamentals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connecting my buttons
        Button myButton = (Button) findViewById(R.id.button);

        // connecting my textViews
        final TextView myText = (TextView) findViewById(R.id.editText);
        final TextView myDispaly = (TextView) findViewById(R.id.textView);
        final TextView myCount = (TextView) findViewById(R.id.countView);

        // declaring my arraylists for use in my function
        final ArrayList<String> myArrayList = new ArrayList<String>();
        final ArrayList<Integer> countList = new ArrayList<Integer>();

        // help debugging
        final String LOG_Tag = "logcatExample";

        // setting up my onclick listener along with the rest of my code
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myTest = "You Tryed me, How was it?";

                // gets data from the text entry
                String myInput = String.valueOf(myText.getText());

                // sets the hint
                myText.setHint(myTest);

                // debugging
                Log.d(LOG_Tag, myInput);
                String myLabel = myInput;

                // displays the item entered
                myDispaly.setText(myLabel);

                //resets the textView back to the hint
                myText.setText("");

                // conditional to determine if the entry is unique
                if (myArrayList.contains(myInput)){

                    // if not unique sets text to this sting
                    myDispaly.setText("This entry is already in the list");
                } else {

                    //adds to entry to the list
                    myArrayList.add(myInput);

                    // gets the size of the list
                    // number of entries
                    int size = myArrayList.size();

                    // number of charecters in eack entry
                    int charCount = myInput.length();

                    // adds the number of charecters to an array of ints
                    countList.add(charCount);

                    // initilizes some varibales for use later
                    int summed = 0;
                    int i=0;

                   // loop to add the total ints in the array
                    while (i < countList.size()) {

                        // adds the total charecters in the array for all entries
                        summed = summed + countList.get(i);
                        int div;

                        // divides total cahrecters in the array by the total number of entries
                        // gets the average charecters
                        div = summed/size;
                        i++;
                        // sets the textView to the average charecters 
                        myCount.setText(Integer.toString(div));
                    }



                   // myCount.setText(new StringBuilder().append("There are ").append(Integer.toString(size)).append(" item(s) in your list").toString());
                }

            }
        });

    }
}
