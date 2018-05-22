package com.karr.mybmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by us on 5/18/2018.
 */

public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bmi_info.db";
    private static final String CREATE_QUERY = "create table " + BmiCalculate.BmiEntry.TABLE_NAME +
            "(" + BmiCalculate.BmiEntry.ID + " integer primary key autoincrement," + BmiCalculate.BmiEntry.WEIGHT + " text," +
            BmiCalculate.BmiEntry.HEIGHT + " text," + BmiCalculate.BmiEntry.BLOOD + " text," +
            BmiCalculate.BmiEntry.DATE + " text," + BmiCalculate.BmiEntry.CATEGORY + " text," +
            BmiCalculate.BmiEntry.BMI + " text);";


    DbOperations(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
        Log.d("Database operations", "Database created..");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created..");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addBmi(SQLiteDatabase db, String weight, String height, String blood, String category, String bmi, String date) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BmiCalculate.BmiEntry.WEIGHT, weight);
        contentValues.put(BmiCalculate.BmiEntry.HEIGHT, height);
        contentValues.put(BmiCalculate.BmiEntry.BLOOD, blood);
        contentValues.put(BmiCalculate.BmiEntry.CATEGORY, category);
        contentValues.put(BmiCalculate.BmiEntry.BMI, bmi);
        contentValues.put(BmiCalculate.BmiEntry.DATE, date);

        db.insert(BmiCalculate.BmiEntry.TABLE_NAME, null, contentValues);
        Log.d("Database operations", "Your data successfully record");
    }

    public Cursor getBmi(SQLiteDatabase db) {

        String[] projections = {BmiCalculate.BmiEntry.ID, BmiCalculate.BmiEntry.DATE, BmiCalculate.BmiEntry.WEIGHT, BmiCalculate.BmiEntry.HEIGHT, BmiCalculate.BmiEntry.BLOOD, BmiCalculate.BmiEntry.BMI, BmiCalculate.BmiEntry.CATEGORY};
        Cursor cursor = db.query(BmiCalculate.BmiEntry.TABLE_NAME, projections, null, null, null, null, null);


        return cursor;
    }

    public void deleteTable(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BmiCalculate.BmiEntry.TABLE_NAME, " id =" + id, null);
    }
}
