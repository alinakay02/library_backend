package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

    private static final long serialVersionUID = 1290619677305030945L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_AUTHOR")
    @SequenceGenerator(name = "SEQ_AUTHOR", sequenceName = "SEQ_AUTHOR", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 32, nullable = false)
    private String surname;

    @Column(name = "PATRONYMIC", length = 32)
    private String patronymic;

}