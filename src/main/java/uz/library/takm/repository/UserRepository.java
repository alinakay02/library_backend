package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByLogin(String login);

    List<User> findAll();

    Optional<User> findByLogin(String login);

}
