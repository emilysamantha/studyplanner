package com.example.demo.categories;

import com.example.demo.events.SuggestedEvent;
import com.example.demo.events.TargetEvent;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
public abstract class Category implements Serializable {
    protected @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Long id;
    protected String title;
    protected String suggestedEventTitle;
    @Column(length = 1024)
    protected ArrayList<TargetEvent> targetEvents;
    protected ArrayList<SuggestedEvent> suggestedEvents;
    protected int priority;

    Category(String title, String suggestedEventTitle) {
        this.title = title;
        this.suggestedEventTitle = suggestedEventTitle;
        this.priority = 0;
        this.targetEvents = new ArrayList<>();
        this.suggestedEvents = new ArrayList<>();
    }

    // empty constructor for jpa
    protected Category() {
    }

    // getters for jpa
    public Long getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getSuggestedEventTitle() { return suggestedEventTitle; }

    public ArrayList<TargetEvent> getTargetEvents() {
        return targetEvents;
    }

    public ArrayList<SuggestedEvent> getSuggestedEvents() {
        return suggestedEvents;
    }

    public int getPriority() { return priority; }

    public void addTargetEvent(TargetEvent targetEvent) {
        targetEvents.add(targetEvent);
    }

    public void addSuggestedEvent(SuggestedEvent suggestedEvent) {
        this.suggestedEvents.add(suggestedEvent);
    }

    public void prioritize() {
        this.priority += 1;
    }
}
