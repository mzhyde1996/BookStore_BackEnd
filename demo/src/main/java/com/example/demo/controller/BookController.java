package com.example.demo.controller;

import com.example.demo.data.Book;
import com.example.demo.data.BookRepository;
import com.example.demo.data.Category;
import com.example.demo.data.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "booklist";
    }
//code for ID
    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> getAllBooksJson() {
        return bookRepository.findAll();
    }

    // New method to get one book by ID
    @GetMapping("/api/books/{id}")
@ResponseBody
public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    Book book = bookRepository.findById(id)
            .orElse(null);

    if (book != null) {
        return ResponseEntity.ok(book);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));

        model.addAttribute("book", book);

        return "editbook";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());

        bookRepository.save(existingBook);

        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }
}
