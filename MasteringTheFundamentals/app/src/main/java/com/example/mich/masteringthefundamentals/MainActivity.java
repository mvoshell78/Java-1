package com.example.mich.masteringthefundamentals;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connecting my buttons
        Button myButton = (Button) findViewById(R.id.button);
        Button myButton2 = (Button) findViewById(R.id.button2);
        // connecting my textFields
        final TextView myText = (TextView) findViewById(R.id.editText);
        final TextView mySearchText = (TextView) findViewById(R.id.editText2);

        //connceting my textViews
        final TextView myDispaly = (TextView) findViewById(R.id.textView);
        final TextView myCount = (TextView) findViewById(R.id.countView);
        final TextView myAverage = (TextView) findViewById(R.id.averageView);


        // declaring my arraylists for use in my function
        final ArrayList<String> myArrayList = new ArrayList<String>();
        final ArrayList<Integer> countList = new ArrayList<Integer>();

        // help debugging
        final String LOG_Tag = "logcatExample";

        // setting up my onclick listener for my 1st button along with the rest of my code
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myTest = "Enter another Value";

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

                    //sets the hint for the search bar to the index of the list
                    if (Integer.toString(size).equals("1")){
                        mySearchText.setHint("Search");
                    } else {
                        mySearchText.setHint("search using a number between 0 and " + (size - 1));
                    }

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
                        myAverage.setText(new StringBuilder().append("With an Average of ").append(Integer.toString(div)).append(" Charecters in each entry").toString());
                    }


                    if (Integer.toString(size).equals("1")) {
                        myCount.setText(new StringBuilder().append("There is ").append(Integer.toString(size)).append(" item in your list").toString());
                    } else {
                        myCount.setText(new StringBuilder().append("There are ").append(Integer.toString(size)).append(" items in your list").toString());
                    }
                }

            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the size of the list
                // number of entries
                int size = myArrayList.size();

                String myInput2 = String.valueOf(mySearchText.getText());

                int i = Integer.parseInt(myInput2);
               
                if (i >= 0){
                    if (i < size){
                        Builder builder = new Builder(MainActivity.this);
                        builder.setTitle("You've selected");
                        builder.setMessage(myArrayList.get(i));
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create();
                        builder.show();
                        mySearchText.setHint("Search Again");
                        mySearchText.setText("");
                    } else {
                        Builder builder2 = new Builder(MainActivity.this);
                        builder2.setTitle("Error");
                        builder2.setMessage("this is not a valid search");
                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder2.create();
                        builder2.show();
                        mySearchText.setHint("Search Again");
                        mySearchText.setText("");
                    }

                }



            }
        });

    }
}
