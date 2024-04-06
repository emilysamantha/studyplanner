package com.example.demo.events;

import com.example.demo.calendars.Calendar;

import java.time.LocalDate;
import java.time.LocalTime;

public class SuggestedEvent extends Event {
    private TargetEvent targetEvent;

    public SuggestedEvent(String title, TargetEvent targetEvent, LocalDate date, LocalTime time, LocalTime duration) {
        super(title, date, time, duration);
        this.targetEvent = targetEvent;
    }

    @Override
    public void addToCalendar(Calendar calendar) {
        calendar.add(this);
    }

    @Override
    public String getString() {
        return title + " (" + targetEvent.title + ") - " + date.toString() + " at " + time.toString() + " to " + time.plusHours(duration.getHour()).plusMinutes(duration.getMinute()).toString();
    }
}
