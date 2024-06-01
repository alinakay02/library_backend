package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.dto.EventDto;
import uz.library.takm.model.Event;
import uz.library.takm.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    // Внедрение зависимости через конструктор для EventRepository
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Метод для получения всех мероприятий, возвращает список DTO
    @Transactional(readOnly = true)
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream()
            .map(this::convertToEventDto) // Преобразование каждой сущности в DTO
            .collect(Collectors.toList());
    }

    // Метод для получения мероприятий текущего месяца
    @Transactional(readOnly = true)
    public List<EventDto> findEventsByDateBetween(LocalDate start, LocalDate end) {
        return eventRepository.findByDateBetween(start, end).stream()
            .map(this::convertToEventDto) // Преобразование каждой сущности в DTO
            .collect(Collectors.toList());
    }

    // Вспомогательный метод для преобразования сущности Event в EventDto
    private EventDto convertToEventDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setEvent(event.getEvent());
        dto.setDate(event.getDate());
        dto.setPhoto(event.getPhoto());
        return dto;
    }
}
