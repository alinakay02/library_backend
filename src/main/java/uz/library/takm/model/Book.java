package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 8756300442409334315L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_BOOK")
    @SequenceGenerator(name = "SEQ_BOOK", sequenceName = "SEQ_BOOK", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE", length = 64)
    private String title;

    @Column(name = "YEAR", length = 4)
    private String year;

    @Column(name = "PUBLISHER", length = 64)
    private String publisher;

    @Column(name = "PDF_PATH", length = 512)
    private String pdfPath;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "BOOK_AUTHORS",
        joinColumns = @JoinColumn(name = "BOOK_ID"),
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private List<Author> authors;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "BOOK_GENRES",
        joinColumns = @JoinColumn(name = "BOOK_ID"),
        inverseJoinColumns = @JoinColumn(name = "GENRE_ID")
    )
    private List<Genre> genres;

}