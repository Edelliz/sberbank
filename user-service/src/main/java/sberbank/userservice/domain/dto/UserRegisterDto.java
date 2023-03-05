package sberbank.userservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sberbank.userservice.domain.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private String login;
    private String password;
    private UserRole role;
    private String name;
    private String surname;
}
