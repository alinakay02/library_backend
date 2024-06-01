package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

    private static final long serialVersionUID = -9191445447484204459L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIB_ITEM")
    @SequenceGenerator(name = "SEQ_LIB_ITEM", sequenceName = "SEQ_LIB_ITEM", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE", length = 128, nullable = false)
    private String title;

    @Column(name = "NEWS", length = 2048, nullable = false)
    private String news;

    @Column(name = "DATE")
    private Instant date;

    @Column(name = "PHOTO")
    private String photo;

}