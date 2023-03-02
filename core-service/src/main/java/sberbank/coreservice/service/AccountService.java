package sberbank.coreservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sberbank.coreservice.domain.entity.AccountEntity;
import sberbank.coreservice.domain.entity.TypeOfOperation;
import sberbank.coreservice.exception.InsufficientFundsException;
import sberbank.coreservice.exception.NotFoundAccount;
import sberbank.coreservice.repository.AccountRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Data
public class AccountService {

    private final AccountRepository accountRepository;
    private final HistoryOperationsService historyService;
    private Random rnd = new Random();

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

        historyService.recordOperation(entity.getId(), TypeOfOperation.OPENING_AN_ACCOUNT, entity.getNumber());

        return entity.getNumber();
    }

    /**
     * Закрытие счета
     */
    @Transactional
    public void deleteAccount(Long accountId) {
        historyService.recordOperation(
                accountId, TypeOfOperation.CLOSING_AN_ACCOUNT, accountRepository.getNumber(accountId)
        );
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

}
