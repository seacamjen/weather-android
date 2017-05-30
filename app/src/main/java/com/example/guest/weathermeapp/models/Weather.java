package com.example.guest.weathermeapp.models;


public class Weather {
    private String mDescription;
    private double mTemp;
    private String mCity;

    public Weather(String description, double temp, String city) {
        this.mDescription = description;
        this.mTemp = temp;
        this.mCity = city;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getTemp() {
        return mTemp;
    }

    public String getCity() {
        return mCity;
    }

}
