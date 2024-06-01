package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.library.takm.repository.OrderBookRepository;

import java.time.LocalDate;
import java.time.Month;

@Service
public class OrderBookService {

    private final OrderBookRepository orderBookRepository;

    @Autowired
    public OrderBookService(OrderBookRepository orderBookRepository) {
        this.orderBookRepository = orderBookRepository;
    }

    // Метод для подсчета заявок на бронирование книг за текущий календарный год
    public long countOrdersForCurrentYear() {
        LocalDate now = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(now.getYear(), Month.JANUARY, 1);
        LocalDate endOfYear = LocalDate.of(now.getYear(), Month.DECEMBER, 31);
        return orderBookRepository.countByDateBetween(startOfYear, endOfYear);
    }
}
