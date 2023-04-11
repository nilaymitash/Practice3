package com.example.practice3.model;

import java.io.Serializable;

public class Feedback implements Serializable {
    private float rating;
    private String review;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
