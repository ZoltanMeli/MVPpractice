package com.ml.zszabo.primera.Model;

public class Conversor {

    private double miles;

    public double getKilometers() {
        return miles * 1.60934;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }
}
