package sberbank.employeeservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegister {
    private String login;
    private String password;
    private String role;
    private String name;
    private String surname;
}
