package sberbank.employeeservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sberbank.employeeservice.domain.dto.Credit;
import sberbank.employeeservice.service.CreditService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;
    @GetMapping()
    public List<Credit> getCreditList() {
        return creditService.getCreditList();
    }
    @PostMapping("/create")
    public Credit createCredit(@RequestBody Credit request) {
        return creditService.createCredit(request);
    }
}
