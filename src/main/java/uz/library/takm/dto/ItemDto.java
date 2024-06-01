package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String title;
    private String news;
    private LocalDate date;
    private String photo;
}
