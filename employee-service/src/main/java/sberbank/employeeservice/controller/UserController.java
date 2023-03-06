package sberbank.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sberbank.employeeservice.domain.dto.BlockClient;
import sberbank.employeeservice.domain.dto.ListUsers;
import sberbank.employeeservice.domain.dto.UserRegister;
import sberbank.employeeservice.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ListUsers getUsersList() {
        return userService.getUsersList();
    }
    @GetMapping("/role")
    public int getUsersRole() {
        return userService.getUsersRole();
    }
    @PostMapping("/create")
    public void createUser(@RequestBody UserRegister request) {
        userService.createUser(request);
    }
    @PostMapping("/block")
    public void blockUser(@RequestBody BlockClient request) {
         userService.blockUser(request);
    }
    @PostMapping("/unblock")
    public void unblockUser(@RequestBody BlockClient request) {
        userService.unblockUser(request);
    }
}
