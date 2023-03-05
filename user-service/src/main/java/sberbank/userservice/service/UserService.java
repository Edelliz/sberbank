package sberbank.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.userservice.domain.dto.UserDto;
import sberbank.userservice.domain.entity.UserEntity;
import sberbank.userservice.exception.NotFoundUser;
import sberbank.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public String getFullNameUser(Long id) {

        return userRepository.getFullNameUserById(id);
    }

    @Transactional(readOnly = true)
    public String blockUser(Long employeeId, Long userId) {
        return userRepository.getFullNameUserById(userId);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {

        return userEntityToDto(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUser("Пользователь с id: " + userId + " не найден"))
        );
    }

    private UserDto userEntityToDto(UserEntity entity) {

        return new UserDto(
                entity.getLogin(), entity.getName(),
                entity.getRole().getDescription(), entity.getStatus().getDescription(),
                entity.getSurname()
        );
    }
}