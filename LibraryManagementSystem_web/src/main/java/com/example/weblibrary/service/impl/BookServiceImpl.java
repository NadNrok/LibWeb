package com.example.weblibrary.service.impl;

import com.example.weblibrary.dto.BookDTO;
import com.example.weblibrary.entity.Author;
import com.example.weblibrary.entity.Book;
import com.example.weblibrary.repo.AuthorRepository;
import com.example.weblibrary.repo.BookRepository;
import com.example.weblibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

   public void editBook(BookDTO bookDTO){

       Book book = bookRepository.findById(bookDTO.getId())
               .orElseThrow(() -> new IllegalArgumentException("Couldn't find book by id:" + bookDTO.getId()));
       Author author = authorRepository.findByName(bookDTO.getAuthorName())
               .orElseThrow(()-> new IllegalArgumentException("Couldn't fid Author by name: " + bookDTO.getAuthorName()));

       book.setAuthor(author);
       book.setName(bookDTO.getName());
       book.setReleaseDate(bookDTO.getReleaseDate());
       book.setPrice(bookDTO.getPrice());
       book.setPages(bookDTO.getPages());
       bookRepository.save(book);
   }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Couldn't find book by id:" + id));
    }
}
