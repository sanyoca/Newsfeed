package com.example.sanya.newsfeed;
/*
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
*/

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import static com.example.sanya.newsfeed.UtilClass.isConnected;

public class SectionFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Articles>> {

    final static String stringGuardianURL = "https://content.guardianapis.com/search?api-key=test&tag=";
    final static String stringGuardianPagesize = "&page-size=";
    String stringFullURL;
    View rootView;
    ListView listArticles;
    TextView emptyView;
    ArticleAdapter adapterArticles;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sectionlayout, container, false);
        listArticles = (ListView) rootView.findViewById(R.id.list_articles);
        emptyView = (TextView) rootView.findViewById(R.id.emptyview);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_loadarticles);

        adapterArticles = new ArticleAdapter(getActivity(), new ArrayList<Articles>());
        listArticles.setAdapter(adapterArticles);
        listArticles.setEmptyView(rootView.findViewById(R.id.emptyview));
        listArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // visit the clicked book website
                Articles articleToVisit = adapterArticles.getItem(position);
                // if we had an internet connection
                if (isConnected(getActivity())) {
                    // then try to visit the book's website
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleToVisit.getWebURL())));
                    } catch (NullPointerException e) {
                        Log.i("SecFragment/onitemclick", e.getMessage());
                        Toast.makeText(getActivity(), "Bad website", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    adapterArticles.clear();
                    ((TextView)rootView.findViewById(R.id.emptyview)).setText("No internet");
                }

            }
        });

        SharedPreferences preferredSections = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String stringSection = getArguments().getString("section");
        String stringMaxArticles = preferredSections.getString("max_articles", "12");
        int intLoaderId = getArguments().getInt("sectionLoader");

        stringFullURL = stringGuardianURL + stringSection + "/" + stringSection + stringGuardianPagesize + stringMaxArticles;
        LoaderManager lm = getActivity().getSupportLoaderManager();
        Log.i("test", "before loader");
        lm.initLoader(intLoaderId, null, this);
        Log.i("test", "after loader");
        return rootView;
    }


    @Override
    public Loader<List<Articles>> onCreateLoader(int id, Bundle args) {
        // if returned from a book's website, we have to delete the adapter's content, cause it re-reads and appends the datas to the existing adapter datas
        adapterArticles.clear();
        Log.i("test", "after clear");
        // make the progressbar appear
        progressBar.setVisibility(View.VISIBLE);
        Log.i("test", "after set visible");
        // and the emptyview disappear
        emptyView.setVisibility(View.INVISIBLE);
        Log.i("test", "after set invisible");
        // let's start it. Async. In the background. On a different thread.
        // return the fetched data to onLoadFinished
        return new ArticleLoader(getActivity(), stringFullURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Articles>> loader, List<Articles> data) {
        adapterArticles.clear();

        if(data != null && !data.isEmpty())    {
            adapterArticles.addAll(data);
        }   else    {
            // if there are no datas, set the emptyview text
            emptyView.setText("No articles");
            // but it can be the lack of internet connection
            if(!isConnected(getActivity())) {
                // then change the emptyview text again
                emptyView.setText("No internet");
            }
        }
        // and make the progressbar woooosh!
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<Articles>> loader) {
        adapterArticles.clear();
    }
}
