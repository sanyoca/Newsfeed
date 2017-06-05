package com.example.sanya.newsfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sanya on 2017.06.05..
 */

public class ArticleAdapter extends ArrayAdapter<Articles> {

    public ArticleAdapter(Activity context, ArrayList<Articles> articles)    {super(context, 0, articles);}

    static class ArticleHolder {
        TextView text_title;
        TextView text_published;
        TextView text_apicontent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArticleHolder holdArticle;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.articlelayout, parent, false);

            holdArticle = new ArticleHolder();
            holdArticle.text_title = (TextView) convertView.findViewById(R.id.text_title);
            holdArticle.text_published = (TextView) convertView.findViewById(R.id.text_published);
            holdArticle.text_apicontent = (TextView) convertView.findViewById(R.id.text_apicontent);
            convertView.setTag(holdArticle);
        } else {
            holdArticle = (ArticleHolder) convertView.getTag();
        }

        Articles actualArticle = getItem(position);

        if (actualArticle != null) {
            holdArticle.text_title.setText(actualArticle.getArticleTitle());
            holdArticle.text_published.setText(actualArticle.getPublishedDate());
            holdArticle.text_apicontent.setText(actualArticle.getApiURL());
        }
        return convertView;
    }
}
