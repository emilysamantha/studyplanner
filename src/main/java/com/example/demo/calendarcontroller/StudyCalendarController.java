package com.example.demo.calendarcontroller;

import com.example.demo.calendars.StudyCalendar;
import com.example.demo.events.SuggestedEvent;
import com.example.demo.repository.CalendarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudyCalendarController {
    private final CalendarRepository calendarRepository;

    StudyCalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/calendars")
    List<StudyCalendar> all() {
        return calendarRepository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/calendars/{id}")
    StudyCalendar one(@PathVariable Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException(id));
    }

    @GetMapping("/calendars/{id}/studyplan")
    ArrayList<SuggestedEvent> calendarStudyPlan(@PathVariable Long id) {
        StudyCalendar studyCalendar = one(id);
        studyCalendar.generatePlan();
        return studyCalendar.getSuggestedEvents();
    }

    // TODO - test posting new calendars
    @PostMapping("/calendars")
    StudyCalendar newCalendar(@RequestBody StudyCalendar newCalendar) {
        return calendarRepository.save(newCalendar);
    }
}
