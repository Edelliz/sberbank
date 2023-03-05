package sberbank.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.userservice.domain.dto.UserLogInDto;
import sberbank.userservice.domain.dto.UserRegisterDto;
import sberbank.userservice.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDto dto) {
        authService.register(dto);
    }

    @PostMapping("/login")
    public void logIn(@RequestBody UserLogInDto dto) {
        authService.logIn(dto);
    }
}
