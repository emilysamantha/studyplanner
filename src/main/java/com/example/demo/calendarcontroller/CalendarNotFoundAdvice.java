package com.example.demo.calendarcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CalendarNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CalendarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String calendarNotFoundHandler(CalendarNotFoundException ex) {
        return ex.getMessage();
    }
}
