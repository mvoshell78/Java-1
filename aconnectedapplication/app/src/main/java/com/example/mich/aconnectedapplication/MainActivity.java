package com.example.mich.aconnectedapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;




public class MainActivity extends AppCompatActivity {

    TextView myText;
    TextView myText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView) findViewById(R.id.textView);

        myText2 = (TextView) findViewById(R.id.textView2);




        if (isOnline()) {
           backTask task = new backTask();
            task.execute();
        } else {

        }
    }


    protected boolean isOnline() {

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            Toast.makeText(this, "We're Connected", Toast.LENGTH_LONG).show();
            return true;
        } else{
            Toast.makeText(this,"We're  not Connected", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public class backTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            myText.setText("i'm pre");
        }

        @Override
        protected String doInBackground(String... params) {
            // The URL string that points to our web resource.
            String urlString = "http://www.recipepuppy.com/api/";

            // Creating the URL object that points to our web resource.
            URL url = null;

            // Establish a connection to the resource at the URL.
            HttpURLConnection connection = null;
            String resourceData = "test";
            try {
                url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                // Setting connection properties.
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(10000); // 10 seconds
                connection.setReadTimeout(10000); // 10 seconds
                // Refreshing the connection.
                connection.connect();

                // Optionally check the status code. Status 200 means everything went OK.
                int statusCode = connection.getResponseCode();

                // Getting the InputStream with the data from our resource.
                InputStream stream = connection.getInputStream();

                // Reading data from the InputStream using the Apache library.
                resourceData = IOUtils.toString(stream);

                // Cleaning up our connection resources.
                stream.close();
                connection.disconnect();
                return resourceData;
            } catch (IOException e) {
                e.printStackTrace();
            }




            return resourceData;

            // The resourceData string should now have our data.


        }

        @Override
        protected void onPostExecute(String s) {
            myText2.setText(s);
        }
    }

}
