package com.example.sanya.newsfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionAdapter extends FragmentPagerAdapter {
    Context mContext;
    int intTabCounter = 0;
    int[] intTabLabels;

    public SectionAdapter(Context context, FragmentManager FM, boolean[] preferences)  {
        super(FM);
        mContext = context;
        intTabLabels = new int[preferences.length];
        int intLabelCounter = 0;
        for(int i = 0; i<=preferences.length-1; i++)  {
            if(preferences[i])  {
                intTabCounter++;
                intTabLabels[intLabelCounter] = i;
                intLabelCounter ++;
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        SectionFragment sFragment = new SectionFragment();
        bundle.putString("section", getPageTitle(position).toString());
        sFragment.setArguments(bundle);
        return sFragment;
    }

    @Override
    public int getCount() {
        return intTabCounter;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] stringTabLabels = mContext.getResources().getStringArray(R.array.tablabels);
        return stringTabLabels[intTabLabels[position]];
    }
}
