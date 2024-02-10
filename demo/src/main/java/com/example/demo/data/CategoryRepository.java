package com.example.demo.data;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can add custom query methods here if needed
}
