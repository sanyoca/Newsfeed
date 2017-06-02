package com.example.sanya.newsfeed;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
    }

    public static class NewsFeedPreferences extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.menu_items);
            Preference interestedSport = findPreference("interested_sport");
            Preference interestedPolitics = findPreference("interested_politics");
            Preference interestedTechnology = findPreference("interested_technology");
            Preference interestedBusiness = findPreference("interested_business");
            Preference interestedEnvironment = findPreference("interested_environment");
            Preference interestedLifestyle = findPreference("interested_lifestyle");
            Preference interestedFashion = findPreference("interested_fashion");

            bindPreferenceSummaryToValue(interestedSport);
            bindPreferenceSummaryToValue(interestedPolitics);
            bindPreferenceSummaryToValue(interestedTechnology);
            bindPreferenceSummaryToValue(interestedBusiness);
            bindPreferenceSummaryToValue(interestedEnvironment);
            bindPreferenceSummaryToValue(interestedLifestyle);
            bindPreferenceSummaryToValue(interestedFashion);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            boolean preferenceString = preferences.getBoolean(preference.getKey(), false);
            onPreferenceChange(preference, preferenceString);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
