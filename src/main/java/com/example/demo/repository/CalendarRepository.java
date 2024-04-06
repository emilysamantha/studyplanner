package com.example.demo.repository;

import com.example.demo.calendars.StudyCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<StudyCalendar, Long> {

}
