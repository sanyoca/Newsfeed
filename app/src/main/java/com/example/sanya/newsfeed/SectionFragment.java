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
        preferredSections.getBoolean("interested_sport", false);
        // TODO: Csinálni a preferences között egy értéket, ami azt tárolja, mennyi cikket mutasson az oldalon. Default: 12.
        String stringFullURL = stringGuardianURL + getArguments().getString("section") + "/" + getArguments().getString("section") + stringGuardianPagesize + "20";
        ((TextView)rootView.findViewById(R.id.texthere)).setText(stringFullURL);
        return rootView;
    }
}
