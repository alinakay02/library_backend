package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
