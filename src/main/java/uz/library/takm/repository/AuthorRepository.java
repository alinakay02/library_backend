package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
