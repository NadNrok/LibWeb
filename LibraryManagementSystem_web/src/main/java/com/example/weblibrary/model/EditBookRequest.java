package com.example.weblibrary.model;

public class EditBookRequest {
    private Long id;

    private String name;

    private int pages;

    private int price;

    private String releaseDate;

    private int numOfBooks;

    private Long authorId;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }

    public int getPrice() {
        return price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }

    public Long getAuthorId() {
        return authorId;
    }



    public void setName(String qweqweqwe) {
        this.name=qweqweqwe;
    }

}
