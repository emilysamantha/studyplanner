package com.example.demo.eventcontroller;

public class EventNotFoundException extends RuntimeException {
    EventNotFoundException(Long id) {
        super("Could not find event " + id);
    }
}

