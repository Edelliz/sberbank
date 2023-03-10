package sberbank.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.userservice.domain.dto.UserDto;
import sberbank.userservice.domain.entity.UserEntity;
import sberbank.userservice.domain.enums.UserStatus;
import sberbank.userservice.exception.NotFoundUser;
import sberbank.userservice.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /*todo убрать*/
    @Transactional(readOnly = true)
    public String getFullNameUser(Long id) {

        return userRepository.getFullNameUserById(id);
    }

    @Transactional
    public void blockUser(Long employeeId, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUser("Пользователь с id: " + userId + " не найден"))
                .setStatus(UserStatus.BLOCK);
    }

    @Transactional
    public void unblockUser(Long employeeId, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUser("Пользователь с id: " + userId + " не найден"))
                .setStatus(UserStatus.ACTIVE);
    }

    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {

        return userEntityToDto(userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUser("Пользователь с id: " + userId + " не найден"))
        );
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllClients() {

        return userRepository.getAllClients().stream().map(this::userEntityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllEmployees() {

        return userRepository.getAllEmployees().stream().map(this::userEntityToDto).collect(Collectors.toList());
    }

    private UserDto userEntityToDto(UserEntity entity) {

        return new UserDto(
                entity.getId(),
                entity.getLogin(), entity.getName(),
                entity.getRole().getDescription(), entity.getStatus().getDescription(),
                entity.getSurname()
        );
    }

}
