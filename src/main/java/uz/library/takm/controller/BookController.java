package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.BookDto;
import uz.library.takm.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Добавление новой книги
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return ResponseEntity.ok().build();
    }

    // Получение списка всех книг
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
