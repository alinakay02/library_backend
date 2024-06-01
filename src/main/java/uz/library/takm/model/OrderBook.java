package uz.library.takm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "ORDER_BOOK")
public class OrderBook implements Serializable {

    private static final long serialVersionUID = -2573883308020842892L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIB_ORDER_BOOK")
    @SequenceGenerator(name = "SEQ_LIB_ORDER_BOOK", sequenceName = "SEQ_LIB_ORDER_BOOK", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @Column(name = "STATE")
    private Boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @Column(name = "DATE")
    private LocalDate date;
}
