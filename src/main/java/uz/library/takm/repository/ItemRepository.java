package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.library.takm.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Метод для получения всех новостей с сортировкой по дате
    List<Item> findAllByOrderByDateDesc();
}
