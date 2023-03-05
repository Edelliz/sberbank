package sberbank.coreservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sberbank.coreservice.domain.entity.AccountEntity;
import sberbank.coreservice.domain.entity.TypeOfOperation;
import sberbank.coreservice.domain.entity.dto.AccountAmountUpdateDto;
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
     * Получение счетов пользователя
     */
    @Transactional(readOnly = true)
    public List<AccountDto> getAccount(Long userId) {

        return accountRepository.findAccountEntitiesByOwnerId(userId).stream()
                .map(this::accountEntityToDto).collect(Collectors.toList());
    }

    /**
     * Открытие счета
     */
    @Transactional
    public AccountDto createAccount(Long clientId) {
        AccountEntity entity = new AccountEntity();
        entity.setAmount(0L);
        entity.setNumber(generateAccountNumber());
        entity.setOwnerId(clientId);

        accountRepository.save(entity);

        return accountEntityToDto(entity);
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
    public AccountDto replenishAccount(AccountAmountUpdateDto dto) {

        return updateAccountAmount(dto.getAccountId(), dto.getAmount());
    }

    /**
     * Снятие счета
     */
    @Transactional
    public AccountDto withdrawAccount(AccountAmountUpdateDto dto) {
        if (accountRepository.getAmount(dto.getAccountId()) -dto.getAmount() >= 0) {

            return updateAccountAmount(dto.getAccountId(), -dto.getAmount());
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

    private AccountDto updateAccountAmount(Long accountId, Double amount) {
        AccountEntity entity = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    throw new NotFoundAccount("Счет с таким id: " + accountId + " не найден");
                });

        entity.setAmount((long) (entity.getAmount() + amount));

        if (amount >= 0) {
            historyService.recordOperation(
                    accountId, TypeOfOperation.ACCOUNT_REPLENISHMENT, amount.toString()
            );
        } else {
            historyService.recordOperation(
                    accountId, TypeOfOperation.WITHDRAWAL_OF_FUNDS_FROM_ACCOUNT, Double.toString(-amount)
            );
        }

        return accountEntityToDto(entity);
    }

    private AccountDto accountEntityToDto(AccountEntity entity) {

        return new AccountDto(
                entity.getId(), entity.getNumber(), getUserFullName(entity.getOwnerId()), entity.getAmount()
        );
    }

    private String getUserFullName(Long userId) {

        return restTemplate.getForObject(URI_TO_USER + "/" + userId, String.class);
    }

}
