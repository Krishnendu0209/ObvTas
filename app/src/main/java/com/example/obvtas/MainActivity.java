package com.example.obvtas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.obvtas.adapter.ImageAdapter;
import com.example.obvtas.fragment.ImageDetailsFragment;
import com.example.obvtas.model.ImageDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private String imageDetailsJSONString;
    private ArrayList<ImageDetails> imageDetailsList = new ArrayList<>(); //All details will be added
    private ArrayList<String> imageLinks = new ArrayList<>(); // All image links will be stored in the list
    private ArrayList<String> imageTitles = new ArrayList<>(); // All image titles will be stored in the list
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageDetailsJSONString = loadJSONFromAsset();
        if(!imageDetailsJSONString.equals("")) //If the loadJSONFromAsset does not return null then start parsing the data stored in the string variable
        {
            parseStringJSONData(); //The parsing will begin
        }
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), imageTitles, imageLinks); //Adapter has been instantiated with necessary parameters
        gridView = findViewById(R.id.grid_view); //Grid view initialised
        gridView.setAdapter(adapter); // The image adapter is called to get set with the data
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() //When any of the item is the following code is executed
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //When an item is clicked the Image Details are displayed
                displayImageDetails(imageDetailsList.get(position));
            }
        });
    }
    /* The function is responsible for passing an Image object to the fragment to display the respective image details
    */
    private void displayImageDetails(ImageDetails imageDetails)
    {
        gridView.setVisibility(View.GONE);//When an item is selected the same frame layout in which the grid view is there, in that itself the fragment_image_details.xml will populate itself
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder,
                ImageDetailsFragment.newInstance(imageDetails))//The ImageDetails Fragment is called
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .addToBackStack("Image Details").commit();
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
        } catch(IOException ex)
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
                imageLinks.add(imageObject.getString("url"));// The array list will finally contain all the links of the images
                imageDetailsList.add(imageDetails); //All the images' details are added as objects are added one by one to the list
                imageTitles.add(imageObject.getString("title"));
            }
        } catch(JSONException e)
        {
            e.printStackTrace();
        }
    }
    public void onBackPressed() //Overriding it so that when back button is clicked again the grid view is made visible
    {
        super.onBackPressed();
        gridView.setVisibility(View.VISIBLE);//The Grid View should get visible when back is pressed
    }
}
