package uz.library.takm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Table(name = "EVENT")
public class Event implements Serializable {

    private static final long serialVersionUID = -7330288850486423399L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIB_EVENT")
    @SequenceGenerator(name = "SEQ_LIB_EVENT", sequenceName = "SEQ_LIB_EVENT", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE", length = 128, nullable = false)
    private String title;

    @Column(name = "EVENT", length = 2048, nullable = false)
    private String event;

    @Column(name = "DATE")
    private Instant date;

    @Column(name = "PHOTO")
    private String photo;

}