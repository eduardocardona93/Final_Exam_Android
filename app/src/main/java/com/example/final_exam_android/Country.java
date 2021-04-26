package com.example.final_exam_android;

public class Country {
    // attributes definition
    private String name;
    private String capital;
    private int flagImg;

    // constructor
    public Country(String name, String capital, int flagImg) {
        this.name = name;
        this.capital = capital;
        this.flagImg = flagImg;
    }
    // getters
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getFlagImg() {
        return flagImg;
    }
}
