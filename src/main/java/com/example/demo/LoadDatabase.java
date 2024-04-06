package com.example.demo;

import com.example.demo.calendars.StudyCalendar;
import com.example.demo.categories.Subject;
import com.example.demo.events.BusyEvent;
import com.example.demo.events.TargetEvent;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CalendarRepository calendarRepository, CategoryRepository subjectRepository, EventRepository eventRepository) {
        // Initialise StudyCalendar
        StudyCalendar studyCalendar1 = new StudyCalendar(LocalDate.of(2024, 4, 1), 7, 9, 18);

        // Initialise Subjects
        Subject parallelComputing = new Subject("Parallel Computing");
        Subject programmingParadigms = new Subject(("Programming Paradigms"));
        Subject advancedDatabaseDesign = new Subject("Advanced Database Design");

        // Add TargetEvents to each Subject
        int numSuggestedEvents = 2;
        LocalTime endOfDay = LocalTime.of(23, 55);
        LocalTime emptyDuration = LocalTime.of(0, 0);

        // parallelComputing TargetEvents
        TargetEvent parallelComputingA1 = new TargetEvent("Assignment 1", LocalDate.of(2024, 5, 7), endOfDay, emptyDuration, numSuggestedEvents);
        TargetEvent parallelComputingLab1 = new TargetEvent("Lab 1", LocalDate.of(2024, 5, 5), endOfDay, emptyDuration, numSuggestedEvents);
        eventRepository.save(parallelComputingA1);
        eventRepository.save(parallelComputingLab1);
        parallelComputing.addTargetEvent(parallelComputingA1);
        parallelComputing.addTargetEvent(parallelComputingLab1);

        // programmingParadigms TargetEvents
        TargetEvent programmingParadigmsQ1 = new TargetEvent("Quiz 1", LocalDate.of(2024, 5, 4), endOfDay, emptyDuration	, numSuggestedEvents);
        eventRepository.save(programmingParadigmsQ1);
        programmingParadigms.addTargetEvent(programmingParadigmsQ1);

        // advancedDatabaseDesign TargetEvents
        TargetEvent databaseA1 = new TargetEvent("Assignment 1", LocalDate.of(2024, 4, 3), endOfDay, emptyDuration, numSuggestedEvents);
        eventRepository.save(databaseA1);
        advancedDatabaseDesign.addTargetEvent(databaseA1);

        // Add priority to Subjects
        parallelComputing.prioritize();
        advancedDatabaseDesign.prioritize();

        subjectRepository.save(parallelComputing);
        subjectRepository.save(programmingParadigms);
        subjectRepository.save(advancedDatabaseDesign);

        studyCalendar1.addCategory(parallelComputing);
        studyCalendar1.addCategory(programmingParadigms);
        studyCalendar1.addCategory(advancedDatabaseDesign);

        // Add BusyEvents to Calendar
        BusyEvent work1 = new BusyEvent("Morning Shift", LocalDate.of(2024, 4, 1), LocalTime.of(7, 0), LocalTime.of(5, 0));
        BusyEvent work2 = new BusyEvent("Closing Shift", LocalDate.of(2024, 4, 4), LocalTime.of(14, 0), LocalTime.of(5, 0));
        eventRepository.save(work1);
        eventRepository.save(work2);

        studyCalendar1.add(work1);
        studyCalendar1.add(work2);

        calendarRepository.save(studyCalendar1);

        return args -> {
            log.info("Preloading calendar");
        };
    }
}
