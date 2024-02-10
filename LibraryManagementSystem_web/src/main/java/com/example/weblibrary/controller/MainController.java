package com.example.weblibrary.controller;

import com.example.weblibrary.entity.Author;
import com.example.weblibrary.entity.Book;
import com.example.weblibrary.entity.RecommendedBook;
import com.example.weblibrary.repo.AuthorRepository;
import com.example.weblibrary.repo.BookRepository;
import com.example.weblibrary.repo.RecommendedBookRepository;
import com.example.weblibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;


@Controller
public class MainController {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final RecommendedBookRepository recommendedBookRepository;
    private final BookService bookService;


    @Autowired
    public MainController(BookRepository bookRepository, AuthorRepository authorRepository,
                          RecommendedBookRepository recommendedBookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.recommendedBookRepository = recommendedBookRepository;
        this.bookService = bookService;
    }


    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("title", "Головна сторінка");
        return "main";
    }


    @GetMapping("/all_books")
    public String allBooks(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("title", "Всі книги");
        return "all-books";
    }

    @GetMapping("/all_authors")
    public String allAuthors(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("title", "Всі автори");
        return "all-authors";
    }

    @GetMapping("/cabinet")
    public String cabinet(Model model, Principal principal) {
        model.addAttribute("title", "Особистий кабінет");
        model.addAttribute("username", principal.getName());
        return "cabinet";
    }


    @GetMapping("/recommend_book")
    public String recommendBook(Model model) {
        model.addAttribute("title", "Замовити книгу");
        return "recommend-book";
    }


    @GetMapping("/recommended_books")
    public String allRecommendedBooks(Model model) {
        Iterable<RecommendedBook> recommendedBooks = recommendedBookRepository.findAll();
        model.addAttribute("recommendedBook", recommendedBooks);
        model.addAttribute("title", "Рекомендованы книги");
        return "recommended-books";
    }


    @GetMapping("/all_authors/{authorId}")
    public String getBooksByAuthor(@PathVariable(value = "authorId") long id, Model model) {
        model.addAttribute("title", "Інформація про автора");
        Author author = authorRepository.findById(id).orElse(null);
        List<Book> result = author.getBooks();
        model.addAttribute("book", result);
        return "author-details";
    }


}
