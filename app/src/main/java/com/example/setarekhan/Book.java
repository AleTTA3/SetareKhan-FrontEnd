
package com.example.setarekhan;

import java.io.Serializable;

public class Book implements Serializable {
    public String id;
    private final String title;
    private final String author;
    private final String description;
    private final String imagePath;

    public Book(String id, String title, String author, String description, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.imagePath = imagePath;
    }


    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getDescription() { return description; }
    public String getImagePath() { return imagePath; }

    public String getId() { return id;}

}
