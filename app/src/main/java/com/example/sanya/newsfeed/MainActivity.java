package com.example.sanya.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{
    boolean[] booleanSectionPreferences = new boolean[7];
    public static Context parentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferredSections = PreferenceManager.getDefaultSharedPreferences(this);
        booleanSectionPreferences[0] = preferredSections.getBoolean("interested_sport", false);
        booleanSectionPreferences[1] = preferredSections.getBoolean("interested_politics", false);
        booleanSectionPreferences[2] = preferredSections.getBoolean("interested_technology", false);
        booleanSectionPreferences[3] = preferredSections.getBoolean("interested_business", false);
        booleanSectionPreferences[4] = preferredSections.getBoolean("interested_environment", false);
        booleanSectionPreferences[5] = preferredSections.getBoolean("interested_lifestyle", false);
        booleanSectionPreferences[6] = preferredSections.getBoolean("interested_fashion", false);

        ViewPager VP = (ViewPager) findViewById(R.id.viewpager);
        SectionAdapter SA = new SectionAdapter(this, getSupportFragmentManager(), booleanSectionPreferences);
        VP.setAdapter(SA);

        TabLayout TL = (TabLayout) findViewById(R.id.tabs);
        TL.setupWithViewPager(VP);

        parentContext = getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_settings)    {
            Intent startSettings = new Intent(this, PreferencesActivity.class);
            startActivity(startSettings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
