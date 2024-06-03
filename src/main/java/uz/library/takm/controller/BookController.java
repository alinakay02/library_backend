package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.library.takm.dto.BookDto;
import uz.library.takm.model.Book;
import uz.library.takm.service.BookService;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

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

    @PostMapping("/{bookId}/upload")
    public ResponseEntity<String> uploadBookPdf(@PathVariable Long bookId, @RequestParam("file") MultipartFile file) {
        bookService.uploadBookPdf(bookId, file);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/{bookId}/pdf")
    public ResponseEntity<Resource> getBookPdf(@PathVariable Long bookId) {
        Resource file = bookService.getBookPdf(bookId);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    // Получение списка всех книг
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
