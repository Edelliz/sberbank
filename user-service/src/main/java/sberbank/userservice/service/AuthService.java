package sberbank.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.userservice.domain.dto.UserLogInDto;
import sberbank.userservice.domain.dto.UserRegisterDto;
import sberbank.userservice.domain.entity.UserEntity;
import sberbank.userservice.domain.enums.UserStatus;
import sberbank.userservice.exception.NotFoundUser;
import sberbank.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional()
    public void register(UserRegisterDto dto) {
        UserEntity entity = new UserEntity(
                dto.getLogin(),dto.getName(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getRole(), UserStatus.ACTIVE,
                dto.getSurname()
        );

        userRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public void logIn(UserLogInDto dto) {
        userRepository.findByLogin(dto.getLogin())
                .orElseThrow(() -> new NotFoundUser("Введены некорректные данные"));
    }
}
