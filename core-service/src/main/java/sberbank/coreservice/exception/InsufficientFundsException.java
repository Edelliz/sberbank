package sberbank.coreservice.exception;

/**
 * Ошибка о недостатке средств
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(final String message) {
        super(message);
    }
}
