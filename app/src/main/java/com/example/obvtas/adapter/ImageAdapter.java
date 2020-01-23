package com.example.obvtas.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.obvtas.R;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        imageTitle = (TextView)imageGrid.findViewById(R.id.grid_text);
        imageView = imageGrid.findViewById(R.id.grid_image);        imageTitle.setText(imageTitles.get(position));
        loadMapPreview(imageURLs.get(position)); //Image will be loaded from the URL
//        if(view != null)
//        {
//            imageGrid = inflater.inflate(R.layout.image_grid, null);
//            initViews(view);// All UI views will be initialised
//            imageTitle.setText(imageTitles.get(position));
//            loadMapPreview(imageURLs.get(position)); //Image will be loaded from the URL
//        }
//        else
//        {
//            imageGrid = (View) view;
//        }

        return imageGrid;
    }

    public void initViews(View view)
    {

    }

    public void loadMapPreview(final String imageUrl)
    {
        //start a background thread for networking
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    //download the drawable
                    final Drawable drawable = Drawable.createFromStream((InputStream) new URL(imageUrl).getContent(), "src");
                    //edit the view in the UI thread
                    imageView.post(new Runnable()
                    {
                        public void run()
                        {
                            imageView.setImageDrawable(drawable);
                        }
                    });
                } catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
