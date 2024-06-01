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

    // Получение всех активных заявок на бронирование книг
    @Transactional(readOnly = true)
    public List<OrderBookDto> findActiveOrders() {
        return orderBookRepository.findActiveOrders().stream()
            .map(this::convertToOrderBookDto)
            .collect(Collectors.toList());
    }

    // Преобразование OrderBook в OrderBookDto с заполнением всех необходимых полей
    private OrderBookDto convertToOrderBookDto(OrderBook orderBook) {
        OrderBookDto dto = new OrderBookDto();
        dto.setId(orderBook.getId());
        dto.setBookId(orderBook.getBook().getId());            // Задаем ID книги
        dto.setUserId(orderBook.getUser().getId());            // Задаем ID пользователя
        dto.setState(orderBook.getState());                    // Состояние заявки
        dto.setDate(orderBook.getDate());                      // Дата бронирования

        dto.setBookTitle(orderBook.getBook().getTitle());      // Наименование книги
        dto.setAuthorsNames(orderBook.getBook().getAuthors().stream()
            .map(a -> a.getName() + " " + a.getSurname())
            .collect(Collectors.joining(", ")));
        dto.setUserName(orderBook.getUser().getName());        // Имя пользователя
        dto.setUserSurname(orderBook.getUser().getSurname());  // Фамилия пользователя
        dto.setUserPatronymic(orderBook.getUser().getPatronymic()); // Отчество пользователя
        dto.setUserCardId(orderBook.getUser().getCardId());    // ID-карта пользователя
        return dto;
    }

}
