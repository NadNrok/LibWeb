package com.example.weblibrary.controller;

import com.example.weblibrary.dto.BookDTO;
import com.example.weblibrary.entity.Author;
import com.example.weblibrary.entity.Book;
import com.example.weblibrary.entity.RecommendedBook;
import com.example.weblibrary.repo.AuthorRepository;
import com.example.weblibrary.repo.BookRepository;
import com.example.weblibrary.repo.RecommendedBookRepository;
import com.example.weblibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookManipulationController {

    private final BookRepository bookRepository;
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final RecommendedBookRepository recommendedBookRepository;

    @Autowired
    public BookManipulationController(BookRepository bookRepository, BookService bookService, AuthorRepository authorRepository, RecommendedBookRepository recommendedBookRepository) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.recommendedBookRepository = recommendedBookRepository;
    }


    @GetMapping("/all_books/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(Model model) {
        model.addAttribute("title", "Додати книгу");
        return "add-book";
    }

    @PostMapping("/book/{id}/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String boorDelete(@PathVariable(value = "id") long id) {
        Book book = bookRepository.findById(id).orElseThrow(null);
        bookRepository.delete(book);
        return "redirect:/all_books";
    }

    @PostMapping("/all_books/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(@RequestParam String author, @RequestParam String name, @RequestParam int pages, @RequestParam int price, @RequestParam String releaseDate) {
        Author newAuthor = new Author(author);
        Book book = new Book(newAuthor, name, pages, price, releaseDate);
        authorRepository.save(newAuthor);
        bookRepository.save(book);
        return "add-book";
    }

    @GetMapping("/book/{id}")
    public String showBookInfo(@PathVariable(value = "id") long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> result = new ArrayList<>();
        book.ifPresent(result::add);
        model.addAttribute("book", result);
        model.addAttribute("title", "Інформація про книгу");

        return "book-details";
    }

    @GetMapping("/book/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(@PathVariable(value = "id") long id, Model model) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = convertBookToBookDTO(book);
        model.addAttribute("book", bookDTO);
        return "edit-book";
    }

    private BookDTO convertBookToBookDTO(Book book) {
        return BookDTO.builder()
                .authorName(book.getAuthor().getName())
                .name(book.getName())
                .pages(book.getPages())
                .price(book.getPrice())
                .releaseDate(book.getReleaseDate())
                .id(book.getId())
                .build();
    }

    @PostMapping("/book/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(BookDTO bookDTO) {
        bookService.editBook(bookDTO);
        return "redirect:/all_books";
    }

    @PostMapping("/recommend_book")
    public String recommendBook(@RequestParam String author, @RequestParam String name) {
        RecommendedBook recommendedBook = new RecommendedBook(author, name);
        recommendedBookRepository.save(recommendedBook);
        return "redirect:/recommended_books";
    }

    @PostMapping("/recommended_books/{id}/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String recommendedBookDelete(@PathVariable(value = "id") long id) {
        RecommendedBook recommendedBook = recommendedBookRepository.findById(id).orElseThrow(null);
        recommendedBookRepository.delete(recommendedBook);
        return "redirect:/recommended_books";
    }
}

