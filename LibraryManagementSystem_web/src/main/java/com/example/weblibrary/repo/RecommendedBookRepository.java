package com.example.weblibrary.repo;


import com.example.weblibrary.entity.RecommendedBook;
import org.springframework.data.repository.CrudRepository;

public interface RecommendedBookRepository extends CrudRepository <RecommendedBook, Long> {

}
