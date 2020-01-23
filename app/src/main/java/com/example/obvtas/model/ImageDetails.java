package com.example.obvtas.model;

/* This is the  model class handling all the components of each item in the JSON array.
    All getter --> to get the data for the respective field
    and the setter -- > to set the data for the respective fields are declared
 */
public class ImageDetails
{
    public String copyRight;
    public String date;
    public String explanation;
    public String hdURL;
    public String mediaType;
    public String serviceVersion;
    public String title;
    public String url;
    
    public String getCopyRight()
    {
        return copyRight;
    }

    public void setCopyRight(String copyRight)
    {
        this.copyRight = copyRight;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getExplanation()
    {
        return explanation;
    }

    public void setExplanation(String explanation)
    {
        this.explanation = explanation;
    }

    public String getHdURL()
    {
        return hdURL;
    }

    public void setHdURL(String hdURL)
    {
        this.hdURL = hdURL;
    }

    public String getMediaType()
    {
        return mediaType;
    }

    public void setMediaType(String mediaType)
    {
        this.mediaType = mediaType;
    }

    public String getServiceVersion()
    {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion)
    {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}