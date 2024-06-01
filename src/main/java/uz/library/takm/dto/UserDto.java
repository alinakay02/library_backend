package uz.library.takm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String name;
    private String surname;
    private String login;
    private String password;

}
