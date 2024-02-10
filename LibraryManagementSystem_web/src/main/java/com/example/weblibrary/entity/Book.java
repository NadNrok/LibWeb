package com.example.weblibrary.entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int pages;

    private String second;

    private int price;

    @Column (name = "release_date")
    private String releaseDate;

    @Column (name = "num_of_books")
    private int numOfBooks;

    @ManyToOne
    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = release_date;
    }

    public int getNumOfBooks() {
        return numOfBooks;
    }

    public void setNumOfBooks(int num_of_books) {
        this.numOfBooks = num_of_books;
    }

    public Book() {
    }

    public Book(Author author, String name, int pages, int price, String releaseDate) {
        this.author = author;
        this.name = name;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
    }

}
