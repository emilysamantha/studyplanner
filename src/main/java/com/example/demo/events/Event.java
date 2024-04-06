package com.example.demo.events;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public abstract class Event implements CalendarEvent, Serializable {
    protected @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    protected String title;
    protected LocalDate date;
    protected LocalTime time;
    protected LocalTime duration;

    protected Event() {
    }

    protected Event(String title, LocalDate date, LocalTime time, LocalTime duration) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public String getString() {
        return title + " - " + date.toString() + " at " + time.toString() + " for " + duration.toString();
    }
}

