package com.example.demo.controller;
import com.example.demo.data.Book;
import com.example.demo.data.BookRepository; // Import your BookService or BookRepository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class BookController {

    @Autowired
    private BookRepository  bookRepository; // Autowire your BookService or BookRepository

    // Add other dependencies if needed

    @GetMapping("/booklist")
    public String showBookList(Model model) {
        List<Book> books = bookRepository.findAll(); // Adjust as per your service/repository
        model.addAttribute("books", books);
        return "booklist";
    }

      @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }
    @GetMapping("/edit/{id}")
public String showEditForm(@PathVariable("id") Long id, Model model) {
    // Fetch the book by id from the repository
    Book book = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));

    // Add the book to the model
    model.addAttribute("book", book);

    // Return the edit page template name
    return "editbook";
}

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @PostMapping("/edit/{id}")
public String editBook(@PathVariable("id") Long id, @ModelAttribute Book updatedBook) {
    // Fetch the existing book from the repository
    Book existingBook = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));

    // Update the existing book with the form data
    existingBook.setTitle(updatedBook.getTitle());
    existingBook.setAuthor(updatedBook.getAuthor());
    existingBook.setIsbn(updatedBook.getIsbn());

    // Save the updated book to the repository
    bookRepository.save(existingBook);

    // Redirect to the book list page
    return "redirect:/booklist";
}

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    // Add other controller methods as needed
}
