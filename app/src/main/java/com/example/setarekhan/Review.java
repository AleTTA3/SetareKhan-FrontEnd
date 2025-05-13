package com.example.setarekhan;

public class Review {
    private String userName;
    private String review;
    private int rating;

    public Review(String userName, String review, int rating) {
        this.userName = userName;
        this.review = review;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }
}
