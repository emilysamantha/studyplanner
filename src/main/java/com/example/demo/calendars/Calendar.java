package com.example.demo.calendars;

import com.example.demo.categories.Category;
import com.example.demo.events.Event;
import com.example.demo.events.SuggestedEvent;
import com.example.demo.events.TargetEvent;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.CategoryRepository;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

@Entity
public abstract class Calendar implements Serializable {
    protected @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(length = 2048)
    protected ArrayList<Category> categories;
    @Column(length = 1024)
    protected ArrayList<Event> events;
    protected ArrayList<SuggestedEvent> suggestedEvents;
    protected LocalDate startDate;
    protected int numDays;
    protected int startHour;
    protected int endHour;

    Calendar(LocalDate startDate, int numDays, int startHour, int endHour) {
        this.startDate = startDate;
        this.numDays = numDays;
        this.startHour = startHour;
        this.endHour = endHour;
        this.events = new ArrayList<Event>();
        this.categories = new ArrayList<Category>();
        this.suggestedEvents = new ArrayList<SuggestedEvent>();
    }

    // empty constructor for jpa.
    protected Calendar() {}

    // getters for jpa.
    public Long getId() { return id; }

    public int getNumDays() { return numDays;}

    public int getStartHour() { return startHour; }

    public int getEndHour() { return endHour; }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<SuggestedEvent> getSuggestedEvents() {
        return suggestedEvents;
    }

    public void addCategory(Category category) {
        categories.add(category);
        // adding SuggestedEvents based on Category priority
        sortCategories(this.categories);
    }

    // adding BusyEvents should be in ascending order.
    public void add(Event event) {
        events.add(event);
    }

    public void add(SuggestedEvent suggestedEvent) {
        events.add(suggestedEvent);
        suggestedEvents.add(suggestedEvent);
    }

    public abstract void generatePlan();

    protected void sortCategories(ArrayList<Category> categories) {
        categories.sort(Comparator.comparingInt(Category::getPriority).reversed());
    }

    // validSuggestedEvent checks if the SuggestedEvent does not clash with any events on the Calendar.
    protected Boolean validSuggestedEvent(SuggestedEvent suggestedEventToCheck) {
        for (Event event : events) {
            if (clash(event, suggestedEventToCheck)) {
                return false;
            }
        }
        for (SuggestedEvent suggestedEvent : suggestedEvents) {
            if (clash(suggestedEvent, suggestedEventToCheck)) {
                return false;
            }
        }
        return true;
    }

    // clash checks if the two events have overlapping times.
    protected Boolean clash(Event event, Event suggestedEvent) {
        // if date is same
        if (event.getDate().isEqual(suggestedEvent.getDate())) {
            // check time
            LocalTime eventEndTime = event.getTime().plusHours(event.getDuration().getHour()).plusMinutes(event.getDuration().getMinute());
            Boolean res = !(suggestedEvent.getTime().isBefore(event.getTime()) || suggestedEvent.getTime().isAfter(eventEndTime));
            return res;
        }
        // otherwise, return false
        return false;
    }

    // isBeforeTargetEvent checks if the SuggestedEvent time is before the TargetEvent.
    protected Boolean isBeforeTargetEvent(SuggestedEvent suggestedEvent, TargetEvent targetEvent) {
        if (suggestedEvent.getDate().isBefore(targetEvent.getDate())) {
            return true;
        }
        if (suggestedEvent.getDate().isEqual(targetEvent.getDate())) {
            // check time
            return suggestedEvent.getTime().isBefore(targetEvent.getTime());
        }
        // otherwise, return false
        return false;
    }

    // helper functions
    protected LocalDate randomDate() {
        Random random = new Random();
        int randNumDays = random.nextInt(numDays);
        return startDate.plusDays(randNumDays);
    }

    protected LocalTime randomTime() {
        Random random = new Random();
        int randHour = random.nextInt(startHour, endHour);
        return LocalTime.of(randHour, 0);
    }

    protected LocalTime randomDuration() {
        Random random = new Random();
        int randHour = random.nextInt(1, 3);
        int randMinute = random.nextInt(2);
        return LocalTime.of(randHour, randMinute * 30);
    }
}
