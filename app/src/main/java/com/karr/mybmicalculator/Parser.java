package com.karr.mybmicalculator;

/**
 * Created by us on 5/15/2018.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, Integer> {

    Context c;
    String data;
    ListView lv;
    int images[] = {R.drawable.food};
    ArrayList<String> names = new ArrayList<>();

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.data = data;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (integer == 1) {
            //BIND TO LISTVIEW
            ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, names);
            lv.setAdapter(adapter);

        } else {
            Toast.makeText(c, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private int parse() {
        try {
            JSONArray ja = new JSONArray(data);
            JSONObject jo = null;

            names.clear();

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                String name = "\n" +
                        "1. " + jo.getString("f_1") + "\n" +
                        "2. " + jo.getString("f_2") + "\n" +
                        "3. " + jo.getString("f_3") + "\n" +
                        "4. " + jo.getString("f_4") + "\n" +
                        "5. " + jo.getString("f_5");
                names.add(name);

            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}