package com.example.obvtas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.obvtas.R;
import com.example.obvtas.model.ImageDetails;

public class ImageDetailsFragment extends Fragment
{
    public ImageDetailsFragment()
    {
        // Required empty public constructor
    }

    public static ImageDetailsFragment newInstance(ImageDetails imageDetails)
    {
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
        return inflater.inflate(R.layout.fragment_image_details, container, false);
    }
}
