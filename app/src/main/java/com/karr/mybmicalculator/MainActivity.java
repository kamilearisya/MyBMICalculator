package com.karr.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText edtHeight, edtWeight;
    Button btnCalculate;
    ArrayAdapter<String> adapter;
    Spinner sp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        sp1 = findViewById(R.id.sp1);

        btnCalculate = findViewById(R.id.btnCalculate);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("AB");
        list.add("O");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this,"Selected: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }

    public void fnCalculate(View view) {

        //check input null or not
        if (TextUtils.isEmpty(edtHeight.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter your height", Toast.LENGTH_LONG);
            toast.show();
            return;
        } else if (TextUtils.isEmpty(edtWeight.getText().toString())) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please enter your weight", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        final String height1 = edtHeight.getText().toString();
        final String weight1 = edtWeight.getText().toString();
        float height = Float.parseFloat(height1);
        float weight = Float.parseFloat(weight1);

        //100*100 for cm to m conversion
        float value = (100 * 100 * weight) / (height * height);

        DecimalFormat twoDForm = new DecimalFormat("#.#");
        Double finalValue = Double.valueOf(twoDForm.format(value));

        String category = "";

        if (finalValue < 18.5) {
            category = "Underweight";
        } else if (finalValue < 25) {
            category = "Normal";
        } else if (finalValue < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        final String valueBmi = Double.toString(finalValue);


        final String blood = sp1.getSelectedItem().toString();


        //Toast.makeText(getApplicationContext(),"weight :"+ weight1 +"\n height :"+ height1+"\n blood :"+ blood+"\n category :"+category+"\n bmi :"+valueBmi, Toast.LENGTH_LONG).show();
        final String finalCategory = category;

        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    String formattedDate = df.format(c.getTime());

                    //save into shared preferences
                    String MY_PREFS_NAME = "MyPrefsFile";
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("weight", weight1);
                    editor.putString("height", height1);
                    editor.putString("blood", blood);
                    editor.putString("category", finalCategory);
                    editor.putString("bmi", valueBmi);
                    editor.putString("date", formattedDate);
                    editor.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent refresh = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(refresh);//Start the same Activity
                        finish(); //finish Activity.
                    }
                });
            }
        };

        Thread thread = new Thread(run);
        thread.start();

    }

    public void fnHistory(View view) {

        Intent i = new Intent(MainActivity.this, DisplayInfoActivity.class);
        startActivity(i);//Start the same Activity
        //finish(); finish Activity.

    }
}
