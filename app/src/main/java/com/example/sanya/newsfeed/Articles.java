package com.example.sanya.newsfeed;

/**
 * Created by sanya on 2017.06.05..
 */

public class Articles {
    private final String stringArticleTitle;
    private final String stringPublicationDate;
    private final String stringURL;
    private final String stringApiURL;

    public Articles(String title, String date, String webURL, String apiURL)    {
        stringArticleTitle = title;
        stringPublicationDate = date;
        stringURL = webURL;
        stringApiURL = apiURL;
    }

    public String getArticleTitle() {   return stringArticleTitle;}

    public String getPublishedDate()    {   return stringPublicationDate;}

    public String getWebURL()  {   return stringURL;}

    public String getApiURL()   {   return stringApiURL;}
}
