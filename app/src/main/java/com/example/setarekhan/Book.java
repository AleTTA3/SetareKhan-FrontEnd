
package com.example.setarekhan;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    public String id;
    private final String title;
    private final String author;
    private final String description;
    private final String imagePath;

    private final List<String> reviews;

    public Book(String id, String title, String author, String description, String imagePath, List<String> reviews) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.imagePath = imagePath;
        this.reviews = reviews;
    }


    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }

    public String getId() { return id;}
    public List<String> getReviews() { return reviews; }

}
