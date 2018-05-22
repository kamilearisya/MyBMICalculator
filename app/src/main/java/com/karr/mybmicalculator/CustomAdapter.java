package com.karr.mybmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    public static ArrayList<String> selectedAnswer;
    Context context;
    String[] questionsList;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] questionsList) {
        this.context = context;
        this.questionsList = questionsList;

        selectedAnswer = new ArrayList<>();
        for (int i = 0; i < questionsList.length; i++) {
            selectedAnswer.add("0");
        }
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return questionsList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.yesno_item, null);

        TextView food1 = view.findViewById(R.id.food);

        food1.setText(questionsList[i]);


        return view;
    }
}

