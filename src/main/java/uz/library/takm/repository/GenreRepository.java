package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
