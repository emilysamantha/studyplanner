package com.example.demo.controller.eventcontroller;

import com.example.demo.events.BusyEvent;
import com.example.demo.events.Event;
import com.example.demo.events.TargetEvent;
import com.example.demo.repository.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    private final EventRepository eventRepository;

    EventController(EventRepository eventRepository) { this.eventRepository = eventRepository; }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/events")
    List<Event> all() {
        return eventRepository.findAll();
    }
    // end::get-aggregate-root[]

    @GetMapping("/events/{id}")
    Event one(@PathVariable Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @PostMapping("/events/busy")
    Event newBusyEvent(@RequestBody BusyEvent busyEvent) {
        return eventRepository.save(busyEvent);
    }

    @PostMapping("/events/target")
    Event newTargetEvent(@RequestBody TargetEvent targetEvent) {
        return eventRepository.save(targetEvent);
    }
}
