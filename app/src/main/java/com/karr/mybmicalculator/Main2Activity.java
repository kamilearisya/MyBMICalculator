package com.karr.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView bmi, categories, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bmi = findViewById(R.id.bmi);
        categories = findViewById(R.id.categories);
        comment = findViewById(R.id.comment);


        //get data from shared preferences
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String category = prefs.getString("category", null);
        String valueBmi = prefs.getString("bmi", null);

        //generate comment
        String comment_r = "";

        if (category == "Underweight") {
            comment_r = "You should eat more.";
        } else if (category == "Normal") {
            comment_r = "Your weight is in ideal range.";
        } else if (category == "Overweight") {
            comment_r = "Try to reduce your weight.";
        } else if (category == "Obese") {
            comment_r = "Eat healthy and physically active. Consult to your doctor.";
        }
        /*else
        {
            comment_r = "";
        }*/


        //display result in text view
        bmi.setText(valueBmi);
        categories.setText(category);
        comment.setText(comment_r);

    }

    public void fnSave(View view) {

        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {

                    //get data from shared preferences
                    String MY_PREFS_NAME = "MyPrefsFile";
                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    String weight1 = prefs.getString("weight", null);
                    String height1 = prefs.getString("height", null);
                    String blood = prefs.getString("blood", null);
                    String category = prefs.getString("category", null);
                    String valueBmi = prefs.getString("bmi", null);
                    String date = prefs.getString("date", null);


                    //Toast.makeText(getApplicationContext(),"weight :"+ weight1 +"\n height1 :"+ height+"\n blood :"+ blood+"\n category :"+category+"\n bmi :"+valueBmi, Toast.LENGTH_LONG).show();


                    //insert into database
                    BackgroundTask backgroundTask = new BackgroundTask(Main2Activity.this);
                    backgroundTask.execute("add_info", weight1, height1, blood, category, valueBmi, date);


                    //save into shared preferences
                    String MY_PREFS_NAME1 = "MyPrefsFile1";
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME1, MODE_PRIVATE).edit();
                    editor.putString("blood", blood);
                    editor.putString("category", category);
                    editor.commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent refresh = new Intent(Main2Activity.this, ResultActivity.class);
                        startActivity(refresh);//Start the same Activity
                        finish();
                    }
                });
            }
        };

        Thread thread = new Thread(run);
        thread.start();


    }
}
