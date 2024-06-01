package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
