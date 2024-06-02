package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
