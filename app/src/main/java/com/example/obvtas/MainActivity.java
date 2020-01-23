package com.example.obvtas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.obvtas.model.ImageDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    private String imageDetailsJSONString;
    private ArrayList <ImageDetails> imageDetailsList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageDetailsJSONString = loadJSONFromAsset();
        if (!imageDetailsJSONString.equals("")) //If the loadJSONFromAsset does not return null then start parsing the data stored in the string variable
        {
            parseStringJSONData(); //The parsing will begin
        }
    }
    /* The function is responsible for reading the data from the JSON file
        and storing it as string for further parsing and processing of the data
    */
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
    /*
        The function is responsible for parsing the JSON array data stored in the string variable
    */
    public void parseStringJSONData()
    {
        try
        {
            JSONArray imageDetailsJSONArray = new JSONArray(imageDetailsJSONString); // The JSON array data is stored in the variable
            for(int i = 0; i < imageDetailsJSONArray.length(); i++)
            {
                JSONObject imageObject = imageDetailsJSONArray.getJSONObject(i); //Every JSON object in the array
                ImageDetails imageDetails = new ImageDetails();
                //imageDetails.setCopyRight(jo_inside.getString("copyright")); -- Commenting the code line since all the objects are not having the key, hence giving problem in adding.
                imageDetails.setDate(imageObject.getString("date")); //Get the date
                imageDetails.setExplanation(imageObject.getString("explanation")); //Get the explanation
                imageDetails.setHdURL(imageObject.getString("hdurl")); //Get the hdurl
                imageDetails.setMediaType(imageObject.getString("media_type")); //Get the media type
                imageDetails.setServiceVersion(imageObject.getString("service_version")); //Get the service version
                imageDetails.setTitle(imageObject.getString("title"));//Get the title
                imageDetails.setUrl(imageObject.getString("url")); //Get the url
                imageDetailsList.add(imageDetails); //All the images' details are added as objects are added one by one to the list
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
}
