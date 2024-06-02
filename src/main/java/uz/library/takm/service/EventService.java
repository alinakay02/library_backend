package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.dto.EventDto;
import uz.library.takm.model.Event;
import uz.library.takm.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional(readOnly = true)
    public List<EventDto> findAllEvents() {
        return eventRepository.findAll().stream()
            .map(this::convertToEventDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventDto> findEventsByDateBetween(LocalDate start, LocalDate end) {
        return eventRepository.findByDateBetween(start, end).stream()
            .map(this::convertToEventDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public EventDto addEvent(EventDto eventDto) {
        Event event = new Event();
        event.setTitle(eventDto.getTitle());
        event.setEvent(eventDto.getEvent());
        event.setDate(eventDto.getDate());
        event.setPhoto(eventDto.getPhoto());
        Event savedEvent = eventRepository.save(event);
        return convertToEventDto(savedEvent);
    }

    @Transactional(readOnly = true)
    public Optional<EventDto> getEventById(Long id) {
        return eventRepository.findById(id).map(this::convertToEventDto);
    }

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
