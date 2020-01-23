package com.example.obvtas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.obvtas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<String> imageTitles = new ArrayList<>();
    private ArrayList<String> imageURLs = new ArrayList<>();
    private TextView imageTitle;
    private ImageView imageView;

    public ImageAdapter(Context c, ArrayList<String> imageTitles, ArrayList<String> imageURLs)
    {
        mContext = c;
        this.imageURLs = imageURLs;
        this.imageTitles = imageTitles;
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return imageURLs.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        View imageGrid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageGrid = inflater.inflate(R.layout.image_grid, null);

        //imageTitle = imageGrid.findViewById(R.id.grid_text); // Commented out the set title as the text view has been removed
        imageView = imageGrid.findViewById(R.id.grid_image);

        //imageTitle.setText(imageTitles.get(position));// Image TEXT is set -- Commented out the set title as the text view has been removed
        loadImages(imageURLs.get(position)); //Image will be loaded from the URL

        return imageGrid;
    }
    /*
       Below function is used to load the images for showing in the GRID Layout
    */
    public void loadImages(final String imageUrl)
    {
        Picasso.get().load(imageUrl).fit().centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
