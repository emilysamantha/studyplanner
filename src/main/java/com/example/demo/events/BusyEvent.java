package com.example.demo.events;

import com.example.demo.calendars.Calendar;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BusyEvent extends Event {
    public BusyEvent(String title, LocalDate date, LocalTime time, LocalTime duration) {
        super(title, date, time, duration);
    }

    protected BusyEvent() {}
}
