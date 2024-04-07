package com.example.demo.controller.calendarcontroller;

import com.example.demo.calendars.Calendar;
import com.example.demo.events.SuggestedEvent;
import com.example.demo.repository.CalendarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CalendarController {
    private final CalendarRepository calendarRepository;

    CalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/calendars")
    List<Calendar> all() {
        return calendarRepository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/calendars/{id}")
    Calendar one(@PathVariable Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new CalendarNotFoundException(id));
    }

    @GetMapping("/calendars/{id}/plan")
    ArrayList<SuggestedEvent> calendarPlan(@PathVariable Long id) {
        Calendar calendar = one(id);
        calendar.generatePlan();
        return calendar.getSuggestedEvents();
    }

    // TODO - test posting new calendars
    @PostMapping("/calendars")
    Calendar newCalendar(@RequestBody Calendar newCalendar) {
        return calendarRepository.save(newCalendar);
    }
}
