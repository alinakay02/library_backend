package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
