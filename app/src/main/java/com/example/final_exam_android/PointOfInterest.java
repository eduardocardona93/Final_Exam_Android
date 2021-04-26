package com.example.final_exam_android;

public class PointOfInterest {
    // attributes definition
    private String country;
    private String name;
    private int imageId;
    private double visitPrice;

    // constructor
    public PointOfInterest(String country, String name, int imageId, double visitPrice) {
        this.country = country;
        this.name = name;
        this.imageId = imageId;
        this.visitPrice = visitPrice;
    }

    // getters

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public double getVisitPrice() {
        return visitPrice;
    }
}
