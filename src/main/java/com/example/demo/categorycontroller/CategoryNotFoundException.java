package com.example.demo.categorycontroller;

public class CategoryNotFoundException extends RuntimeException {
    CategoryNotFoundException(Long id) {
        super("Could not find category " + id);
    }
}
