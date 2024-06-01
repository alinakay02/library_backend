package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.library.takm.dto.OrderBookDto;
import uz.library.takm.service.OrderBookService;

import java.util.List;

@RestController
@RequestMapping("/order-books")
public class OrderBookController {

    private final OrderBookService orderBookService;

    @Autowired
    public OrderBookController(OrderBookService orderBookService) {
        this.orderBookService = orderBookService;
    }

    // Метод для получения количества заявок на бронирование книг за текущий календарный год
    @GetMapping("/count-current-year")
    public long getCountOrdersForCurrentYear() {
        return orderBookService.countOrdersForCurrentYear();
    }

    // Получение всех активных заявок на бронирование книг
    @GetMapping("/new")
    public List<OrderBookDto> getActiveOrders() {
        return orderBookService.findActiveOrders();
    }
}