package com.example.mich.aconnectedapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;




public class MainActivity extends AppCompatActivity {

    TextView latitude;
    TextView longitude;
    TextView latitudeLabel;
    TextView longitudeLabel;
    TextView addressDisplay;
    TextView cityDisplay;
    TextView stateDisplay;

    EditText address;
    EditText city;
    EditText state;
    Button getGeo;
    ProgressBar pb;

    String cityInput;
    String stateInput;
    String addressInput;
    String addressFinal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitude = (TextView) findViewById(R.id.latResult);
        longitude = (TextView) findViewById(R.id.lonResult);
        latitudeLabel = (TextView) findViewById(R.id.laitude);
        latitudeLabel.setVisibility(View.INVISIBLE);
        longitudeLabel = (TextView) findViewById(R.id.longitude);
        longitudeLabel.setVisibility(View.INVISIBLE);

        addressDisplay = (TextView) findViewById(R.id.displayAddress);
        cityDisplay = (TextView) findViewById(R.id.displayCity);
        stateDisplay = (TextView) findViewById(R.id.displayState);

        address = (EditText) findViewById(R.id.street);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        getGeo = (Button) findViewById(R.id.getGeoButton);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        getGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInput = String.valueOf(address.getText());
                cityInput = String.valueOf(city.getText());
                stateInput = String.valueOf(state.getText());

                address.setText("");
                city.setText("");
                state.setText("");

                if (isOnline()) {
                    backTask task = new backTask();
                    task.execute();

                    String[] a = addressInput.split(" +");

                    makeAddressUsable(a);

                } else {

                }
            }
        });




    }

    private void makeAddressUsable(String[] a) {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a.length; i++) {
            sb.append(a[i] + "+");
            addressFinal = sb.toString();

        }
    }


    protected boolean isOnline() {

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            //Toast.makeText(this, "We're Connected", Toast.LENGTH_LONG).show();
            return true;
        } else{
            Toast.makeText(this,"No internet connection", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public class backTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // The URL string that points to our web resource.
            String city = cityInput;
            String state = stateInput;
            String address = addressFinal;
            String urlString = "http://www.yaddress.net/api/address?AddressLine1=" + address + "&AddressLine2=" + city + "+" + state;




            // Creating the URL object that points to our web resource.
            URL url = null;

            // Establish a connection to the resource at the URL.
            HttpURLConnection connection = null;
            String resourceData = "No Data";
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

            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject apiData;

            try{
                apiData = new JSONObject(resourceData);


            } catch(Exception e){
                System.out.println("Cannot convert API resource to JSON");
                apiData = null;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (apiData != null) {

                return apiData;
            }else{
                return null;
            }

        }

        @Override
        protected void onPostExecute(JSONObject s) {
            try {
                pb.setVisibility(View.INVISIBLE);
                String longitude = s.getString("Longitude");
                String Latitude = s.getString("Latitude");
                String address = s.getString("AddressLine1");
                String city = s.getString("City");
                String state = s.getString("State");

                longitudeLabel.setVisibility(View.VISIBLE);
                latitudeLabel.setVisibility(View.VISIBLE);
                MainActivity.this.longitude.setText((CharSequence) longitude);
                latitude.setText((CharSequence) Latitude );
                if (address != null){
                    addressDisplay.setText((CharSequence)address);
                }else {
                    addressDisplay.setText("");
                }
                if (city != null){
                    cityDisplay.setText((CharSequence)city);
                }else {
                    cityDisplay.setText("");
                }
                if (state != null){
                    stateDisplay.setText((CharSequence)state);
                }else {
                    stateDisplay.setText("");
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
