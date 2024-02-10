package com.example.weblibrary.service;

import com.example.weblibrary.dto.BookDTO;
import com.example.weblibrary.entity.Book;


public interface BookService {


     void editBook(BookDTO bookDTO);

    Book getBookById(long id);
}
