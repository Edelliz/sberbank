package sberbank.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.userservice.domain.dto.UserDto;
import sberbank.userservice.service.UserService;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/fullname")
    public String getFullNameUser(@PathVariable Long userId) {
        return userService.getFullNameUser(userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    /*Доработать при добавлении аутентификации*/
    @PostMapping("/{employeeId}/{userId}/block")
    public void blockUser(@PathVariable Long employeeId, @PathVariable Long userId) {
        userService.blockUser(employeeId, userId);
    }

    /*Доработать при добавлении аутентификации*/
    @PostMapping("/{employeeId}/{userId}/unblock")
    public void unblockUser(@PathVariable Long employeeId, @PathVariable Long userId) {
        userService.unblockUser(employeeId, userId);
    }

}
