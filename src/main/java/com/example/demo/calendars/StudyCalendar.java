package com.example.demo.calendars;

import com.example.demo.categories.Category;
import com.example.demo.events.SuggestedEvent;
import com.example.demo.events.TargetEvent;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.CategoryRepository;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class StudyCalendar extends Calendar {

    public StudyCalendar(LocalDate startDate, int numDays, int startHour, int endHour) {
        super(startDate, numDays, startHour, endHour);
    }

    protected StudyCalendar() {}

    @Override
    public void generatePlan() {
        // For each category to plan for
        for (Category category : this.categories) {
            // For each TargetEvent in the category
            for (int i = 0; i < category.getTargetEvents().size(); i++) {
                TargetEvent targetEvent = category.getTargetEvents().get(i);
                // Add targetEvent.numSuggestedEvents to the calendar
                for (int j = 0; j < targetEvent.getNumSuggestedEvents(); j++) {
                    int addedHours = 0;
                    int addedDays = (numDays / targetEvent.getNumSuggestedEvents()) * j;

                    SuggestedEvent suggestedEvent = new SuggestedEvent(
                            category.getSuggestedEventTitle(),
                            targetEvent,
                            startDate.plusDays(addedDays),
                            LocalTime.of(startHour, 0),
                            randomDuration());

                    while (!(validSuggestedEvent(suggestedEvent) && isBeforeTargetEvent(suggestedEvent, targetEvent))) {
                        suggestedEvent = new SuggestedEvent(
                                category.getSuggestedEventTitle(),
                                targetEvent,
                                startDate.plusDays(addedDays),
                                LocalTime.of(startHour, 0).plusHours(addedHours),
                                randomDuration()
                        );
                        addedHours++;
                        // Try next day and reset addedHour
                        if (startHour + addedHours > endHour) {
                            addedDays++;
                            addedHours = 0;
                        }
                        // Try again from the start and reset addedDay and addedHour
                        if (addedDays == numDays) {
                            addedDays = 0;
                            addedHours = 0;
                        }
                    }
                    add(suggestedEvent);
                    category.addSuggestedEvent(suggestedEvent);
                }
            }
        }
    }
}
