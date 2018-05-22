package com.karr.mybmicalculator;

/**
 * Created by us on 5/18/2018.
 */

public final class BmiCalculate {

    BmiCalculate() {
    }

    public static abstract class BmiEntry {
        public static final String ID = "id";
        public static final String WEIGHT = "weight";
        public static final String HEIGHT = "height";
        public static final String BLOOD = "blood";
        public static final String CATEGORY = "category";
        public static final String BMI = "bmi";
        public static final String DATE = "date";

        public static final String TABLE_NAME = "bmi_table";


    }
}
