package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.EventDto;
import uz.library.takm.model.Event;
import uz.library.takm.service.EventService;
import java.util.List;
import java.util.Optional;

@RestController // Определяет класс как REST контроллер, позволяя обрабатывать HTTP запросы
@RequestMapping("/events") // Устанавливает базовый путь для всех методов в этом контроллере
public class EventController {

    private final EventService eventService; // Сервис для управления событиями

    @Autowired // Автоматическое внедрение зависимости для сервиса событий
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAll") // Обработка HTTP GET запроса для получения всех событий
    public List<EventDto> getAllEvents() {
        // Возвращает список всех событий, преобразованных в DTO
        return eventService.findAllEvents();
    }

    @PostMapping("/add") // Обработка HTTP POST запроса для добавления нового события
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        // Добавляет новое событие на основе данных DTO и возвращает добавленное событие в виде DTO
        return eventService.addEvent(eventDto);
    }

    @GetMapping("/{id}") // Обработка HTTP GET запроса для получения события по его ID
    public Optional<EventDto> getEventById(@PathVariable Long id) {
        // Возвращает событие по его ID, обернутое в Optional, чтобы обрабатывать случаи, когда событие не найдено
        return eventService.getEventById(id);
    }

    @PutMapping("/update") // Обработка HTTP PUT запроса для обновления существующего события
    public Event updateEvent(@RequestBody EventDto eventDto) {
        // Обновляет существующее событие на основе данных из DTO и возвращает обновленное событие
        return eventService.updateEvent(eventDto);
    }
}
