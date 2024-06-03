package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.library.takm.dto.AuthorDto;
import uz.library.takm.dto.BookDto;
import uz.library.takm.dto.GenreDto;
import uz.library.takm.model.Author;
import uz.library.takm.model.Book;
import uz.library.takm.model.Genre;
import uz.library.takm.repository.AuthorRepository;
import uz.library.takm.repository.BookRepository;
import uz.library.takm.repository.GenreRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final Path uploadDir;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.uploadDir = Paths.get("/uploads").toAbsolutePath().normalize();

    }

    @Transactional
    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(new String(bookDto.getTitle().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        book.setYear(bookDto.getYear());
        book.setPublisher(new String(bookDto.getPublisher().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        book.setAuthors(convertAuthors(bookDto.getAuthors()));
        book.setGenres(convertGenres(bookDto.getGenres()));
        return bookRepository.save(book);
    }

    private List<Author> convertAuthors(List<AuthorDto> authorDtos) {
        return authorDtos.stream()
            .map(dto -> authorRepository.findByNameAndSurnameAndPatronymic(
                    new String(dto.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    new String(dto.getSurname().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                    new String(dto.getPatronymic().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(new String(dto.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    newAuthor.setSurname(new String(dto.getSurname().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    newAuthor.setPatronymic(new String(dto.getPatronymic().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    return authorRepository.save(newAuthor);
                }))
            .collect(Collectors.toList());
    }

    private List<Genre> convertGenres(List<GenreDto> genreDtos) {
        return genreDtos.stream()
            .map(dto -> genreRepository.findByGenreName(new String(dto.getGenreName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                .orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setGenreName(new String(dto.getGenreName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                    return genreRepository.save(newGenre);
                }))
            .collect(Collectors.toList());
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
            .map(this::convertToBookDto)
            .collect(Collectors.toList());
    }

    public BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(new String(book.getTitle().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        bookDto.setYear(book.getYear());
        bookDto.setPublisher(new String(book.getPublisher().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        bookDto.setPdfPath(new String(book.getPdfPath().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
        bookDto.setAuthors(book.getAuthors().stream()
            .map(author -> {
                AuthorDto authorDto = new AuthorDto();
                authorDto.setId(author.getId());
                authorDto.setName(new String(author.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                authorDto.setSurname(new String(author.getSurname().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                authorDto.setPatronymic(new String(author.getPatronymic().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                return authorDto;
            })
            .collect(Collectors.toList()));
        bookDto.setGenres(book.getGenres().stream()
            .map(genre -> {
                GenreDto genreDto = new GenreDto();
                genreDto.setId(genre.getId());
                genreDto.setGenreName(new String(genre.getGenreName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
                return genreDto;
            })
            .collect(Collectors.toList()));
        return bookDto;
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void uploadBookPdf(Long bookId, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            Path destinationFile = this.uploadDir.resolve(
                    Paths.get(new String(file.getOriginalFilename().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)))
                .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.uploadDir)) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }

            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile);
            }

            Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
            book.setPdfPath(new String(destinationFile.getFileName().toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
            bookRepository.save(book);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }

    public Resource getBookPdf(Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found"));

        try {
            Path filePath = this.uploadDir.resolve(new String(book.getPdfPath().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8)).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filePath);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}
