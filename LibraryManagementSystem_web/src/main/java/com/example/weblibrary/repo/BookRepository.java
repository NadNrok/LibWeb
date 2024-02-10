package com.example.weblibrary.repo;

import com.example.weblibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository <Book, Long> {
}
