package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "GENRE")
public class Genre implements Serializable {

    private static final long serialVersionUID = 5305667698027000393L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_GENRE")
    @SequenceGenerator(name = "SEQ_GENRE", sequenceName = "SEQ_GENRE", allocationSize = 1)
    private Long id;

    @Column(name = "GENRE_NAME", length = 32, nullable = false, unique = true)
    private String genreName;

}