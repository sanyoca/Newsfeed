package com.example.sanya.newsfeed;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SectionFragment extends Fragment {

    final static String stringGuardianURL = "https://content.guardianapis.com/search?api-key=test&tag=";
    final static String stringGuardianPagesize = "&page-size=";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sectionlayout, container, false);

        SharedPreferences preferredSections = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String stringSection = getArguments().getString("section");
        String stringMaxArticles = preferredSections.getString("max_articles", "12");
        // int intLoaderId = getArguments().getInt("sectionLoader");

        String stringFullURL = stringGuardianURL + stringSection + "/" + stringSection + stringGuardianPagesize + stringMaxArticles;
        // getLoaderManager().initLoader(intLoaderId, null, this);
        ((TextView)rootView.findViewById(R.id.texthere)).setText(stringFullURL);
        return rootView;
    }
/*
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, List data) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
*/
}
