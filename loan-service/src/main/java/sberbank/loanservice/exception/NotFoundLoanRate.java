package sberbank.loanservice.exception;

/**
 * Не найден счет
 */
public class NotFoundLoanRate extends RuntimeException {
    public NotFoundLoanRate(final String message) {
        super(message);
    }
}
