package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
}
