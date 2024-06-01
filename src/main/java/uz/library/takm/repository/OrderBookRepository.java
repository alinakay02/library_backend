package uz.library.takm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.library.takm.model.OrderBook;

import java.time.LocalDate;
import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
    // Запрос для получения количества заявок за текущий год
    @Query("SELECT COUNT(o) FROM OrderBook o WHERE o.date BETWEEN ?1 AND ?2")
    long countByDateBetween(LocalDate start, LocalDate end);

    // Запрос для получения заявок с state = null
    @Query("SELECT ob FROM OrderBook ob " +
        "JOIN FETCH ob.book b " +
        "JOIN FETCH b.authors a " +
        "JOIN FETCH ob.user u " +
        "WHERE ob.state IS NULL")
    List<OrderBook> findPendingOrders();

    // Запрос для получения заявок с state = true
    @Query("SELECT ob FROM OrderBook ob " +
        "JOIN FETCH ob.book b " +
        "JOIN FETCH b.authors a " +
        "JOIN FETCH ob.user u " +
        "WHERE ob.state = TRUE")
    List<OrderBook> findApprovedOrders();

}
