package com.example.final_exam_android;

public class Country {
    private String name;
    private String capital;
    private int flagImg;

    public Country(String name, String capital, int flagImg) {
        this.name = name;
        this.capital = capital;
        this.flagImg = flagImg;
    }

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
