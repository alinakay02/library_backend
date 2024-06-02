package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.EventDto;
import uz.library.takm.service.EventService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Получение списка всех мероприятий
    @GetMapping("/getAll")
    public List<EventDto> getAllEvents() {
        return eventService.findAllEvents();
    }

    // Получение списка мероприятий на текущий месяц
    @GetMapping("/currentMonth")
    public List<EventDto> getEventsCurrentMonth() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        return eventService.findEventsByDateBetween(start, end);
    }

    // Добавление мероприятия
    @PostMapping("/add")
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventService.addEvent(eventDto);
    }
}
