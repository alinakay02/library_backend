package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.library.takm.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.date BETWEEN ?1 AND ?2")
    List<Event> findByDateBetween(LocalDate start, LocalDate end);

    List<Event> findAll();
}
