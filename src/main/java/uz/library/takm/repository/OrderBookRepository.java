package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.library.takm.model.OrderBook;

import java.time.LocalDate;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

    @Query("SELECT COUNT(o) FROM OrderBook o WHERE o.date BETWEEN ?1 AND ?2")
    long countByDateBetween(LocalDate start, LocalDate end);
}
