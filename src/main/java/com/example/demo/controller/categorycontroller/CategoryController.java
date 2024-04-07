package com.example.demo.controller.categorycontroller;

import com.example.demo.categories.Category;
import com.example.demo.categories.Subject;
import com.example.demo.events.SuggestedEvent;
import com.example.demo.repository.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;

    CategoryController(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/categories")
    List<Category> all() {
        return categoryRepository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/categories/{id}")
    Category one(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping("/categories/{id}/plan")
    List<SuggestedEvent> categoryPlan(@PathVariable Long id) {
        Category category = one(id);
        return category.getSuggestedEvents();
    }

    // TODO - test posting new subjects
    // TODO - how to add target events
    // TODO - do training plan
    @PostMapping("/categories/subject")
    Category newCategory(@RequestBody Subject subject) {
        return categoryRepository.save(subject);
    }

}
