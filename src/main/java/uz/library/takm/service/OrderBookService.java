package uz.library.takm.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.library.takm.dto.OrderBookDto;
import uz.library.takm.model.OrderBook;
import uz.library.takm.repository.OrderBookRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.scheduling.annotation.Scheduled;

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

    // Получение списка заявок ожидающих одобрения (state = null)
    @Transactional(readOnly = true)
    public List<OrderBookDto> findPendingOrders() {
        return orderBookRepository.findPendingOrders().stream()
            .map(this::convertToOrderBookDto)
            .collect(Collectors.toList());
    }

    // Получение списка одобренных заявок (state = true)
    @Transactional(readOnly = true)
    public List<OrderBookDto> findApprovedOrders() {
        return orderBookRepository.findApprovedOrders().stream()
            .map(this::convertToOrderBookDto)
            .collect(Collectors.toList());
    }

    // Преобразование OrderBook в OrderBookDto с заполнением всех необходимых полей
    private OrderBookDto convertToOrderBookDto(OrderBook orderBook) {
        OrderBookDto dto = new OrderBookDto();
        dto.setId(orderBook.getId());
        dto.setBookId(orderBook.getBook().getId());
        dto.setUserId(orderBook.getUser().getId());
        dto.setState(orderBook.getState());
        dto.setDate(orderBook.getDate());
        dto.setBookTitle(orderBook.getBook().getTitle());
        dto.setYear(orderBook.getBook().getYear());  // Год издания
        dto.setPublisher(orderBook.getBook().getPublisher());  // Издатель
        dto.setAuthorsNames(orderBook.getBook().getAuthors().stream()
            .map(a -> a.getName() + " " + a.getSurname())
            .collect(Collectors.joining(", ")));
        dto.setUserName(orderBook.getUser().getName());
        dto.setUserSurname(orderBook.getUser().getSurname());
        dto.setUserPatronymic(orderBook.getUser().getPatronymic());
        dto.setUserCardId(orderBook.getUser().getCardId());
        return dto;
    }

    // Метод для одобрения заявки на бронирование книги
    @Transactional
    public void approveOrder(Long id) {
        OrderBook orderBook = orderBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderBook.setState(true);
        orderBook.setDate(LocalDate.now());  // Установка текущей даты как даты одобрения
        orderBookRepository.save(orderBook);
    }

    // Метод для отклонения заявки на бронирование книги
    @Transactional
    public void rejectOrder(Long id) {
        OrderBook orderBook = orderBookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        orderBook.setState(false);
        orderBook.setDate(LocalDate.now()); // Установка даты отклонения
        orderBookRepository.save(orderBook);
    }

}
