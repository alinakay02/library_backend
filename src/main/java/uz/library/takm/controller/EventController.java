package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.model.Event;
import uz.library.takm.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Получение списка всех мероприятий
    @GetMapping("/events/getAll")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Получение списка мероприятий на текущий месяц
    @GetMapping("/events/currentMonth")
    public List<Event> getEventsCurrentMonth() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        return eventRepository.findByDateBetween(start, end);
    }
}
