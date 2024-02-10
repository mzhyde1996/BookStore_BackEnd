package com.example.demo.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert example data into the database
        Category fictionCategory = new Category("Fiction");
        Category nonFictionCategory = new Category("Non-Fiction");

        // Save categories to the database
        categoryRepository.save(fictionCategory);
        categoryRepository.save(nonFictionCategory);

        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-3-16-148410-0", fictionCategory);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", fictionCategory);

        // Save the books to the database
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}

