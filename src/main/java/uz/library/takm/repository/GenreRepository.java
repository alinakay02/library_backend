package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreName(String genreName);
}