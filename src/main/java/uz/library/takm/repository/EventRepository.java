package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
