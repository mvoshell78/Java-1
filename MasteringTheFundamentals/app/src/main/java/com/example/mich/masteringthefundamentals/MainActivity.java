package com.example.mich.masteringthefundamentals;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connecting my buttons
        //Button myButton = (Button) findViewById(R.id.button);
        //Button myButton2 = (Button) findViewById(R.id.button2);

        TextView clickText = (TextView) findViewById(R.id.textView2);
        TextView clickText2 = (TextView) findViewById(R.id.textView3);
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
        clickText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String myTest = getString(R.string.enterAnother);

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
                    myDispaly.setText(R.string.dupEntry);
                } else {

                    //adds to entry to the list
                    myArrayList.add(myInput);

                    // gets the size of the list
                    // number of entries
                    int size = myArrayList.size();

                    //sets the hint for the search bar to the index of the list
                    if (Integer.toString(size).equals("1")){
                        mySearchText.setHint(R.string.search);
                    } else {
                        mySearchText.setHint(getString(R.string.setHint) + (size - 1));
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
                        myAverage.setText(new StringBuilder().append(getString(R.string.wAvg)).append(Integer.toString(div)).append(getString(R.string.charEntry)).toString());
                    }


                    if (Integer.toString(size).equals("1")) {
                        myCount.setText(new StringBuilder().append(getString(R.string.thereIs)).append(Integer.toString(size)).append(getString(R.string.itemsList)).toString());
                    } else {
                        myCount.setText(new StringBuilder().append(getString(R.string.thereAre)).append(Integer.toString(size)).append(getString(R.string.itemsList)));
                    }
                }

            }
        });

        clickText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the size of the list
                // number of entries
                int size = myArrayList.size();

                final String myInput2 = String.valueOf(mySearchText.getText());

                final int i = Integer.parseInt(myInput2);

                if (i >= 0) {
                    if (i < size) {
                        Builder builder = new Builder(MainActivity.this);
                        builder.setTitle(R.string.selected);
                        builder.setMessage(myArrayList.get(i));
                        builder.setIcon(R.mipmap.ic_launcher);
                        builder.setNegativeButton(R.string.remove, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myArrayList.remove(i);
                                myDispaly.setText("Item was removed");
                                myAverage.setText("");
                                myCount.setText("");
                            }
                        });
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create();
                        builder.show();
                        mySearchText.setHint(R.string.searchAgain);
                        mySearchText.setText("");
                    } else {
                        Builder builder2 = new Builder(MainActivity.this);
                        builder2.setTitle(R.string.error);
                        builder2.setMessage(R.string.notValid);
                        builder2.setIcon(R.mipmap.ic_launcher);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder2.create();
                        builder2.show();
                        mySearchText.setHint(R.string.searchAgain);
                        mySearchText.setText("");
                    }

                }


            }
        });

    }


    }

