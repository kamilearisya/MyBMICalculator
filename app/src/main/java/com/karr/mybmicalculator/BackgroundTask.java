package com.karr.mybmicalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by us on 5/18/2018.
 */

public class BackgroundTask extends AsyncTask<String, Bmi, String> {

    Context ctx;
    BmiAdapter bmiAdapter;
    Activity activity;
    ListView listView;
    DbOperations dbOperations1;
    String id, date, weight, height, blood, category, bmiR;


    BackgroundTask(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        final DbOperations dbOperations = new DbOperations(ctx);

        if (method.equals("add_info")) {
            String weight = params[1];
            String height = params[2];
            String blood = params[3];
            String category = params[4];
            String bmi = params[5];
            String date = params[6];

            SQLiteDatabase db = dbOperations.getWritableDatabase();
            dbOperations.addBmi(db, weight, height, blood, category, bmi, date);

            return "Your data successfully record";

        } else if (method.equals("get_info")) {
            //display info into list view
            listView = activity.findViewById(R.id.display_listview);


            SQLiteDatabase db = dbOperations.getReadableDatabase();
            Cursor cursor = dbOperations.getBmi(db);

            bmiAdapter = new BmiAdapter(ctx, R.layout.display_info_row);


            while (cursor.moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.ID));
                date = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.DATE));
                weight = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.WEIGHT));
                height = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.HEIGHT));
                blood = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.BLOOD));
                category = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.CATEGORY));
                bmiR = cursor.getString(cursor.getColumnIndex(BmiCalculate.BmiEntry.BMI));

                Bmi bmi = new Bmi(id, date, weight, height, blood, category, bmiR);
                publishProgress(bmi);

            }

            //delete information
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id1) {
                    final String id_1 = id;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setTitle("Clear this record ?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dbOperations.deleteTable(Integer.parseInt(id_1));
                            Intent i = new Intent(ctx, DisplayInfoActivity.class);
                            ctx.startActivity(i);
                            ((Activity) ctx).finish();

                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }


            });
            return "get_info";


        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Bmi... values) {
        bmiAdapter.add(values[0]);

    }

    @Override
    protected void onPostExecute(String result) {
        if (result.equals("get_info")) {
            listView.setAdapter(bmiAdapter);
        } else {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }
}
