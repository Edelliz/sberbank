package sberbank.coreservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sberbank.coreservice.domain.entity.AccountEntity;
import sberbank.coreservice.domain.entity.TypeOfOperation;
import sberbank.coreservice.domain.entity.dto.AccountDto;
import sberbank.coreservice.exception.InsufficientFundsException;
import sberbank.coreservice.exception.NotFoundAccount;
import sberbank.coreservice.repository.AccountRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class AccountService {

    private final AccountRepository accountRepository;
    private final HistoryOperationsService historyService;

    private Random rnd = new Random();
    private final RestTemplate restTemplate = new RestTemplate();

    private final String URI_TO_USER = "http://localhost:8765/user";

    /**
     * Получение всех счетов
     */
    @Transactional(readOnly = true)
    public List<AccountDto> getAccounts() {

        return accountRepository.findAll().stream().map(this::accountEntityToDto).collect(Collectors.toList());
    }

    /**
     * Открытие счета
     */
    @Transactional
    public String createAccount(Long clientId) {
        AccountEntity entity = new AccountEntity();
        entity.setAmount(0L);
        entity.setNumber(generateAccountNumber());
        entity.setOwnerId(clientId);

        accountRepository.save(entity);

        return entity.getNumber();
    }

    /**
     * Закрытие счета
     */
    @Transactional
    public void deleteAccount(Long accountId) {

        accountRepository.deleteById(accountId);
    }

    /**
     * Пополнение счета
     */
    @Transactional
    public Long replenishAccount(Long accountId, Long amount) {

        return updateAccountAmount(accountId, amount);
    }

    /**
     * Снятие счета
     */
    @Transactional
    public Long withdrawAccount(Long accountId, Long amount) {
        if (accountRepository.getAmount(accountId) - amount >= 0) {

            return updateAccountAmount(accountId, -amount);
        }

        throw new InsufficientFundsException("Недостаточно средств для снятия");
    }

    private String generateAccountNumber() {
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int n = rnd.nextInt(10);
            number.append(n);
        }

        return number.toString();
    }

    private Long updateAccountAmount(Long accountId, Long amount) {
        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    throw new NotFoundAccount("Счет с таким id: " + accountId + " не найден");
                });

        entity.setAmount(entity.getAmount() + amount);

        if (amount >= 0) {
            historyService.recordOperation(
                    accountId, TypeOfOperation.ACCOUNT_REPLENISHMENT, amount.toString()
            );
        } else {
            historyService.recordOperation(
                    accountId, TypeOfOperation.WITHDRAWAL_OF_FUNDS_FROM_ACCOUNT, Long.toString(-amount)
            );
        }

        return entity.getAmount();
    }

    private AccountDto accountEntityToDto(AccountEntity entity) {

        return new AccountDto(entity.getNumber(), getUserFullName(entity.getOwnerId()), entity.getAmount());
    }

    private String getUserFullName(Long userId) {

        return restTemplate.getForObject(URI_TO_USER + "/" + userId, String.class);
    }

}
