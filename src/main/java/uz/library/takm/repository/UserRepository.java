package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.User;

import java.util.List;
import java.util.Optional;

// Интерфейс репозитория для объектов User, использующий Spring Data JPA для упрощения работы с базой данных
public interface UserRepository extends JpaRepository<User, Long> {

    // Метод для поиска пользователя по его логину. Возвращает найденного пользователя или null, если пользователь не найден.
    User findUserByLogin(String login);

    // Метод для получения списка всех пользователей в базе данных. Возвращает список пользователей.
    List<User> findAll();

    // Метод для поиска пользователя по логину. Возвращает Optional, который может содержать пользователя или быть пустым, если пользователь не найден.
    Optional<User> findByLogin(String login);

}
