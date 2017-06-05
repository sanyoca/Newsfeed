package com.example.sanya.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.example.sanya.newsfeed.UtilClass.articleDataParsingFromThis;
import static com.example.sanya.newsfeed.UtilClass.getJSONDataFromHere;
import static com.example.sanya.newsfeed.UtilClass.openConnection;
import static com.example.sanya.newsfeed.UtilClass.transformIntoURL;

/**
 * Created by sanya on 2017.06.05..
 */

public class ArticleLoader extends AsyncTaskLoader<List<Articles>> {

    Context mContext;
    String stringGetArticlesFromHere;

    public ArticleLoader(Context context, String articlesURL)   {
        super(context);
        mContext = context;
        stringGetArticlesFromHere = articlesURL;
    }

    /**
     * start the loader
     */
    protected void onStartLoading()	{
        forceLoad();
    }

    /**
     *
     * @return a list of bookdatas to display
     * the stuff we do in the background. Async. On another thread.
     */
    @Override
    public List<Articles> loadInBackground() {
        List<Articles> loadIntoThis;

        URL urlGetDataFromHere;

        // get the URL from the string
        try {
            urlGetDataFromHere = transformIntoURL(stringGetArticlesFromHere);
        }   catch   (MalformedURLException e)   {
            Log.i("loadInBackGround", e.getMessage());
            return null;
        }

        // build up the connection
        HttpURLConnection articleConnection = openConnection(urlGetDataFromHere);
        String stringArticleJSONData;

        // if the connection was successful
        if(articleConnection != null)  {
            // get the JSON data
            stringArticleJSONData = getJSONDataFromHere(articleConnection);
        }   else    {
            // else return a null
            return null;
        }

        // if receiving the JSON data was successful (it's not empty)
        if(!stringArticleJSONData.isEmpty())   {
            // then parse it into a nice List<BookDatas>
            loadIntoThis = articleDataParsingFromThis(getContext(), stringArticleJSONData);
        }   else    {
            // else return null
            return null;
        }
        // return with the parsed data
        return loadIntoThis;
    }
}
