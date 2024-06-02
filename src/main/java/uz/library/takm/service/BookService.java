package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.dto.AuthorDto;
import uz.library.takm.dto.BookDto;
import uz.library.takm.dto.GenreDto;
import uz.library.takm.model.Author;
import uz.library.takm.model.Book;
import uz.library.takm.model.Genre;
import uz.library.takm.repository.AuthorRepository;
import uz.library.takm.repository.BookRepository;
import uz.library.takm.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setYear(bookDto.getYear());
        book.setPublisher(bookDto.getPublisher());
        book.setAuthors(convertAuthors(bookDto.getAuthors()));
        book.setGenres(convertGenres(bookDto.getGenres()));
        return bookRepository.save(book);
    }

    private List<Author> convertAuthors(List<AuthorDto> authorDtos) {
        return authorDtos.stream()
            .map(dto -> authorRepository.findByNameAndSurnameAndPatronymic(dto.getName(), dto.getSurname(), dto.getPatronymic())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(dto.getName());
                    newAuthor.setSurname(dto.getSurname());
                    newAuthor.setPatronymic(dto.getPatronymic());
                    return authorRepository.save(newAuthor);
                }))
            .collect(Collectors.toList());
    }

    private List<Genre> convertGenres(List<GenreDto> genreDtos) {
        return genreDtos.stream()
            .map(dto -> genreRepository.findByGenreName(dto.getGenreName())
                .orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setGenreName(dto.getGenreName());
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
        bookDto.setTitle(book.getTitle());
        bookDto.setYear(book.getYear());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setAuthors(book.getAuthors().stream()
            .map(author -> {
                AuthorDto authorDto = new AuthorDto();
                authorDto.setId(author.getId());
                authorDto.setName(author.getName());
                authorDto.setSurname(author.getSurname());
                authorDto.setPatronymic(author.getPatronymic());
                return authorDto;
            })
            .collect(Collectors.toList()));
        bookDto.setGenres(book.getGenres().stream()
            .map(genre -> {
                GenreDto genreDto = new GenreDto();
                genreDto.setId(genre.getId());
                genreDto.setGenreName(genre.getGenreName());
                return genreDto;
            })
            .collect(Collectors.toList()));
        return bookDto;
    }
}
