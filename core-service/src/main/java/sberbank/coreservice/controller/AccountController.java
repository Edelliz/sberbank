package sberbank.coreservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping()
    public String getUserAccount() {
        return "Some account";
    }
}
