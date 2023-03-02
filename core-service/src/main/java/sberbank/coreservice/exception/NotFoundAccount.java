package sberbank.coreservice.exception;

/**
 * Не найден счет
 */
public class NotFoundAccount extends RuntimeException {
    public NotFoundAccount(final String message) {
        super(message);
    }
}
