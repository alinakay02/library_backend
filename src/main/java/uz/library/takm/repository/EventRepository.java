package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.library.takm.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Метод для получения всех мероприятий с сортировкой по дате
    List<Event> findAllByOrderByDateDesc();

    // Метод для получения мероприятий в заданном диапазоне с сортировкой по возрастанию даты
    List<Event> findByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);
}
