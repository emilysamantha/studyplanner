package com.example.demo.events;

import com.example.demo.calendars.Calendar;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TargetEvent extends Event implements Serializable {
    private int numSuggestedEvents;

    public TargetEvent(String title, LocalDate date, LocalTime time, LocalTime duration, int numSuggestedEvents) {
        super(title, date, time, duration);
        this.numSuggestedEvents = numSuggestedEvents;
    }

    protected TargetEvent() {}

    @Override
    public void addToCalendar(Calendar calendar) {
        calendar.add(this);
    }

    public int getNumSuggestedEvents() { return numSuggestedEvents; }
}
