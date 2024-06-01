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
}
