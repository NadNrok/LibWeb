package com.example.weblibrary.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    public Author(String name) {
        this.name=name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author() {
    }



}
