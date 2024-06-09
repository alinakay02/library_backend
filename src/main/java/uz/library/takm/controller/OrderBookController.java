package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

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

    // Запрос для получения количества заявок на бронирование книг за текущий календарный год
    @GetMapping("/count-current-year")
    public long getCountOrdersForCurrentYear() {
        return orderBookService.countOrdersForCurrentYear();
    }

    // Получение всех активных заявок на бронирование книг
    @GetMapping("/new")
    public ResponseEntity<List<OrderBookDto>> getPendingOrders() {
        return ResponseEntity.ok(orderBookService.findPendingOrders());
    }

    // Получение списка одобренных заявок
    @GetMapping("/approved")
    public ResponseEntity<List<OrderBookDto>> getApprovedOrders() {
        return ResponseEntity.ok(orderBookService.findApprovedOrders());
    }

    // Одобрение заявки на бронирование книги
    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveOrder(@PathVariable Long id) {
        try {
            orderBookService.approveOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Отклонение заявки на бронирование книги
    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectOrder(@PathVariable Long id) {
        try {
            orderBookService.rejectOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}