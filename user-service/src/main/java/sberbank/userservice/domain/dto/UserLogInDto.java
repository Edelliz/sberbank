package sberbank.userservice.domain.dto;

import lombok.Data;

@Data
public class UserLogInDto {
    private String login;
    private String password;
}
