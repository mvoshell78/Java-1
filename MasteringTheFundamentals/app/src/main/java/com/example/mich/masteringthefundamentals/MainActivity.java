package com.example.mich.masteringthefundamentals;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = (Button) findViewById(R.id.button);
        final TextView myText = (TextView) findViewById(R.id.editText);
        final TextView myDispaly = (TextView) findViewById(R.id.textView);

        final String LOG_Tag = "logcatExample";

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myTest = "You Tryed me, How was it?";


                String myInput = String.valueOf(myText.getText());
                myText.setHint(myTest);
                Log.d(LOG_Tag, myInput);
                String myLabel = myInput;
                myDispaly.setText(myLabel);
                myText.setText("");
            }
        });

    }
}
