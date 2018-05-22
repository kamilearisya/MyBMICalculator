package com.karr.mybmicalculator;

/**
 * Created by us on 5/15/2018.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class DataPackager {

    String query, query1;

    public DataPackager(String query, String query1) {
        this.query = query;
        this.query1 = query1;
    }

    public String packageData() {
        JSONObject jo = new JSONObject();
        StringBuffer queryString = new StringBuffer();

        try {
            jo.put("Query", query);
            jo.put("Query1", query1);

            Boolean firstValue = true;
            Iterator it = jo.keys();

            do {
                String key = it.next().toString();
                String value = jo.get(key).toString();

                if (firstValue) {
                    firstValue = false;
                } else {
                    queryString.append("&");
                }

                queryString.append(URLEncoder.encode(key, "UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value, "UTF-8"));

            } while (it.hasNext());

            return queryString.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
