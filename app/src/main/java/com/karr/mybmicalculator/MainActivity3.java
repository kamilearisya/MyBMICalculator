package com.karr.mybmicalculator;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity3 extends AppCompatActivity {

    TextView textView;

    ArrayAdapter<String> adapter;
    String address = "http://192.168.0.129/Nutrition/foodInformation.php";
    InputStream is = null;
    String line = null;
    String result = null;
    String[] data;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //list data
        lv = findViewById(R.id.listView);
        //allow network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        //retrieve
        getData();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), data);
        lv.setAdapter(customAdapter);
    }

    private void getData() {
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //read content into String

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //parse
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            data = new String[ja.length()];

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                //data[i]= "Jenis darah " + jo.getString("jenis_darah")+ "\n"  + "Categori " + jo.getString("categori");
                data[i] = "Blood Type : " + jo.getString("jenis_darah") + "\n" + "Category : " + jo.getString("categori") + "\n" + "BMI range : " + jo.getString("bmi_range") + "\n" + "\n" + "Recommended food : " + "\n" + jo.getString("f_1") + "\n" + jo.getString("f_2") + "\n" + jo.getString("f_3") + "\n" + jo.getString("f_4") + "\n" + jo.getString("f_5");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
