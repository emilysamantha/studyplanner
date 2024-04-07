package com.example.demo.controller.calendarcontroller;

public class CalendarNotFoundException extends RuntimeException {
    CalendarNotFoundException(Long id) {
        super("Could not find calendar " + id);
    }
}
