package com.example.demo.controller.eventcontroller;

public class EventNotFoundException extends RuntimeException {
    EventNotFoundException(Long id) {
        super("Could not find event " + id);
    }
}

