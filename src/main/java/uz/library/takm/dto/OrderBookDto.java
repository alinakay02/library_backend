package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderBookDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private Boolean state;
    private LocalDate date;

    private String bookTitle;         // Наименование книги
    private String authorsNames;      // Имена авторов
    private String userName;          // Имя пользователя
    private String userSurname;       // Фамилия пользователя
    private String userPatronymic;    // Отчество пользователя
    private String userCardId;        // ID-карта пользователя
}
