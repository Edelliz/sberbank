package sberbank.loanservice.exception;

/**
 * Не найден счет
 */
public class NotFoundLoan extends RuntimeException {
    public NotFoundLoan(final String message) {
        super(message);
    }
}
