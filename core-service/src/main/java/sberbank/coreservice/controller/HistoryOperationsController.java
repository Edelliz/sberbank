package sberbank.coreservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sberbank.coreservice.service.HistoryOperationsService;
import sberbank.coreservicecommon.dto.AccountOperationDto;

import java.util.List;

@RestController
@RequestMapping("/history/operations")
@RequiredArgsConstructor
public class HistoryOperationsController {

    private final HistoryOperationsService historyService;

    @GetMapping("/{accountId}")
    public List<AccountOperationDto> getAccountHistory(@PathVariable Long accountId) {
        return historyService.getAccountHistory(accountId);
    }
}
