package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.EventDto;
import uz.library.takm.model.Event;
import uz.library.takm.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    // Добавление мероприятия
    @PostMapping("/add")
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventService.addEvent(eventDto);
    }

    // Получение мероприятия по ID
    @GetMapping("/{id}")
    public Optional<EventDto> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // Обновление мероприятия
    @PutMapping("/update")
    public Event updateEvent(@RequestBody EventDto eventDto) {
        return eventService.updateEvent(eventDto);
    }
}
