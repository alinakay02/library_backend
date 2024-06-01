package uz.library.takm.repository;

import org.springframework.data.repository.CrudRepository;
import uz.library.takm.model.OrderBook;

public interface OrderBookRepository extends CrudRepository<OrderBook, Long> {
}
