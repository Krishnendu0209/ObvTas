package com.example.obvtas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJSONFromAsset();
    }

    public String loadJSONFromAsset()
    {
        String json = null;
        try
        {
            InputStream is = getAssets().open("nasa_data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer); //All the data read from the file is stored in the buffer array
            is.close(); //Reading the JSON file is done, close input stream
            json = new String(buffer, "UTF-8"); //Store the buffer array data to the variable
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            return null; //If any exception occurred then return null
        }
        return json; //If no exception then return the string that store the data.
    }
}
