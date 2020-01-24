package com.example.obvtas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obvtas.R;
import com.example.obvtas.model.ImageDetails;
import com.squareup.picasso.Picasso;

public class ImageDetailsFragment extends Fragment
{
    private TextView imageTitle, imageDetails;
    private ImageView highDefinitionImage;
    private static ImageDetails imageDetail;
    public ImageDetailsFragment()
    {
        // Required empty public constructor
    }

    public static ImageDetailsFragment newInstance(ImageDetails imageDetailObject)
    {
        imageDetail = imageDetailObject;
        return new ImageDetailsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_details, container, false);
        initializeViewReferences(view); //initialize the views
        setData(imageDetail); // set data for displaying in the XML file
        return view;
    }
    /* The function is implemented to handle the data population to the respective views
     */
    private void setData(ImageDetails imageDetail)
    {
        imageTitle.setText(imageDetail.getTitle());
        loadImages(imageDetail.getHdURL()); //Images are loaded
        imageDetails.setText(imageDetail.getExplanation());
    }
    /* The function is responsible for initialising the views declared in the xml file
     */
    private void initializeViewReferences(View view)
    {
        imageTitle = view.findViewById(R.id.imageTitle);
        highDefinitionImage = view.findViewById(R.id.highDefinitionImage);
        imageDetails = view.findViewById(R.id.imageDetails);
    }
    /*
       Below function is used to load the images for showing in the Image Details screen
    */
    private void loadImages(final String imageUrl)
    {
        Picasso.get().load(imageUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(highDefinitionImage);
    }
}
