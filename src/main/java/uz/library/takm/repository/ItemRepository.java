package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
