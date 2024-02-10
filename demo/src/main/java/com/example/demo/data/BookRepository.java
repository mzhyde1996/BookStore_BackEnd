package com.example.demo.data;


import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom query methods here if needed
    
}