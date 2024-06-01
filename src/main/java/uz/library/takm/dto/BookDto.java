package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private List<AuthorDto> authors;
    private List<GenreDto> genres;
}
