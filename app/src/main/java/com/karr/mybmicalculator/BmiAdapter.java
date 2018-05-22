package com.karr.mybmicalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by us on 5/20/2018.
 */

public class BmiAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public BmiAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Bmi object) {
        list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        BmiHolder bmiHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_info_row, parent, false);
            bmiHolder = new BmiHolder();
            bmiHolder.tx_date = row.findViewById(R.id.t_date);
            bmiHolder.tx_weight = row.findViewById(R.id.t_name);
            bmiHolder.tx_height = row.findViewById(R.id.t_price);
            bmiHolder.tx_blood = row.findViewById(R.id.t_qty);
            bmiHolder.tx_category = row.findViewById(R.id.t_category);
            bmiHolder.tx_bmiR = row.findViewById(R.id.t_bmi);

            row.setTag(bmiHolder);


        } else {
            bmiHolder = (BmiHolder) row.getTag();
        }
        Bmi bmi = (Bmi) getItem(position);
        bmiHolder.tx_date.setText(bmi.getDate().toString());
        bmiHolder.tx_weight.setText(bmi.getWeight().toString() + "kg");
        bmiHolder.tx_height.setText(bmi.getHeight().toString() + "cm");
        bmiHolder.tx_blood.setText(bmi.getBlood().toString());
        bmiHolder.tx_category.setText(bmi.getCategory().toString());
        bmiHolder.tx_bmiR.setText(bmi.getBmiR().toString());

        return row;
    }

    static class BmiHolder {
        TextView tx_date, tx_weight, tx_height, tx_blood, tx_category, tx_bmiR;

    }
}
