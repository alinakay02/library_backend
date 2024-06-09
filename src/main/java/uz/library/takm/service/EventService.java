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
        return eventRepository.findAllByOrderByDateDesc().stream()
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

    // Метод для обновления мероприятия
    @Transactional
    public Event updateEvent(EventDto eventDto) {
        Optional<Event> existingEventOpt = eventRepository.findById(eventDto.getId());
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setTitle(eventDto.getTitle());
            existingEvent.setEvent(eventDto.getEvent());
            existingEvent.setDate(eventDto.getDate());
            existingEvent.setPhoto(eventDto.getPhoto());
            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Event not found with id " + eventDto.getId());
        }
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
