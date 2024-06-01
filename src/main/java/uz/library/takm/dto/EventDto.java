package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String title;
    private String event;
    private LocalDate date;
    private String photo;
}
