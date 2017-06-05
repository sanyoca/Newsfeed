package com.example.sanya.newsfeed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanya on 2017.06.05..
 */

public final class UtilClass {

    private UtilClass() {}

    static URL transformIntoURL(String stringURL) throws MalformedURLException {
        return new URL(stringURL);
    }

    static HttpURLConnection openConnection(URL urlConnection)   {
        HttpURLConnection newsConnection = null;

        // try to open a connection
        try {
            newsConnection = (HttpURLConnection) urlConnection.openConnection();
            newsConnection.setReadTimeout(10000);
            newsConnection.setConnectTimeout(15000);
            newsConnection.setRequestMethod("GET");
            newsConnection.connect();

            // is it okay? is the response what we expected?
            if (newsConnection.getResponseCode() == 200) {
                // yes, return with the connection object
                return newsConnection;
            }   else    {
                // no, return a null
                Log.i("loadInBackground", "Error response code: " + newsConnection.getResponseCode());
                return newsConnection;
            }
        } catch (IOException e) {
            // something went awry
            Log.i("loadInBackGround", "Problem opening connection: "+ e.getMessage());
        }
        return newsConnection;
    }

    static String getJSONDataFromHere(HttpURLConnection connection)    {
        StringBuilder articleList = new StringBuilder();

        // try to build a stream
        try {
            InputStream articleStream = connection.getInputStream();
            articleList = new StringBuilder();
            // if it's okay, read
            if (articleStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(articleStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                // until there is no more thing to read
                while (line != null) {
                    articleList.append(line);
                    line = reader.readLine();
                }
            }
        }   catch   (IOException e) {
            // something went awry
            Log.i("getJSONDataFromHere", e.getMessage());
        }
        return articleList.toString();
    }

    static List<Articles> articleDataParsingFromThis(Context context, String stringArticleDatas)   {
        List<Articles> tempNews = new ArrayList<>();

        try {
            JSONObject articleJSON = new JSONObject(stringArticleDatas);
            // get the artcle datas
            JSONObject JSONResponse = articleJSON.getJSONObject("response");
            JSONArray JSONResults = JSONResponse.getJSONArray("results");
            for(int i=0; i< JSONResults.length(); i++)  {
                JSONObject actualArticle = JSONResults.getJSONObject(i);
                String stringArticleTitle = actualArticle.getString("webTitle");
                String stringPublicationDate = actualArticle.getString("webPublicationDate");
                String stringWebURL = actualArticle.getString("webURL");
                String stringApiURL = actualArticle.getString("apiURL");

                Articles newArticle = new Articles(stringArticleTitle, stringPublicationDate, stringWebURL, stringApiURL);
                tempNews.add(newArticle);
            }
        }   catch (JSONException e) {
            Log.i("Util/getData", e.getMessage());
            return null;
        }
        return tempNews;
    }

    static boolean isConnected(Context context)   {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
