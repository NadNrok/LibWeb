package com.example.weblibrary.repo;

import com.example.weblibrary.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AuthorRepository extends CrudRepository <Author, Long> {
    Optional <Author> findByName(String authorName);
}
