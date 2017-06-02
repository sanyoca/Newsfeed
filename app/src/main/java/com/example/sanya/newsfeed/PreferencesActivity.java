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
            Preference maxArticles = findPreference("max_articles");

            bindPreferenceSummaryToValue(0, interestedSport);
            bindPreferenceSummaryToValue(0, interestedPolitics);
            bindPreferenceSummaryToValue(0, interestedTechnology);
            bindPreferenceSummaryToValue(0, interestedBusiness);
            bindPreferenceSummaryToValue(0, interestedEnvironment);
            bindPreferenceSummaryToValue(0, interestedLifestyle);
            bindPreferenceSummaryToValue(0, interestedFashion);
            bindPreferenceSummaryToValue(1, maxArticles);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue)
        {
            if(newValue instanceof String) {
                preference.setSummary(newValue.toString());
            }
            return true;
        }

        private void bindPreferenceSummaryToValue(int varType, Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            if(varType == 0)  {
                boolean preferenceBoolean = preferences.getBoolean(preference.getKey(), false);
                onPreferenceChange(preference, preferenceBoolean);
            }   else    {
                String preferenceString = preferences.getString(preference.getKey(), "12");
                onPreferenceChange(preference, preferenceString);
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}
