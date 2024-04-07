package com.example.demo.categories;

import jakarta.persistence.Entity;

@Entity
public class Subject extends Category {
    public Subject(String title) {
        super(title, "Study for " + title);
    }

    // empty constructor for jpa
    protected Subject() {}

    @Override
    public int getPriority() {
        if (targetEvents != null) {
            return priority + targetEvents.size();
        }
        return priority;
    }
}
