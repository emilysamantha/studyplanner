package com.example.demo.controller.categorycontroller;

public class CategoryNotFoundException extends RuntimeException {
    CategoryNotFoundException(Long id) {
        super("Could not find category " + id);
    }
}
