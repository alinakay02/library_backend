package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
}
