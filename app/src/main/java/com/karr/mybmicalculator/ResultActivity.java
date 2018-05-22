package com.karr.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    String urlAddress = "http://192.168.0.129/nutrition/searcher.php";
    ListView lv;
    ImageView noDataImg, noNetworkImg;
    TextView sp_blood, sp_category;
    int images[] = {R.drawable.food};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //get data from shared preferences
        String MY_PREFS_NAME1 = "MyPrefsFile1";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME1, MODE_PRIVATE);
        String category = prefs.getString("category", null);
        String blood = prefs.getString("blood", null);


        sp_blood = findViewById(R.id.sp_blood);
        sp_category = findViewById(R.id.sp_category);

        //display into text view
        sp_blood.setText(" " + blood + " ");
        sp_category.setText(" " + category);


        lv = findViewById(R.id.lv);
        noDataImg = findViewById(R.id.nodataImg);
        noNetworkImg = findViewById(R.id.noserver);

        String query = blood;
        String query1 = category;

        SenderReceiver sr = new SenderReceiver(ResultActivity.this, urlAddress, query, query1, lv, noDataImg, noNetworkImg);
        sr.execute();


    }

    public void fnSave(View view) {
        Intent i = new Intent(ResultActivity.this, DisplayInfoActivity.class);
        startActivity(i);//Start the same Activity
        //finish(); finish Activity.
    }
}

