package com.karr.mybmicalculator;

/**
 * Created by us on 5/20/2018.
 */

public class Bmi {

    private String id, date, weight, height, blood, category, bmiR;

    public Bmi(String id, String date, String weight, String height, String blood, String category, String bmiR) {
        this.setId(id);
        this.setDate(date);
        this.setWeight(weight);
        this.setHeight(height);
        this.setBlood(blood);
        this.setCategory(category);
        this.setBmiR(bmiR);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBmiR() {
        return bmiR;
    }

    public void setBmiR(String bmiR) {
        this.bmiR = bmiR;
    }
}
