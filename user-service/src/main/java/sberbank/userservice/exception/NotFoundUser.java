package sberbank.userservice.exception;

/**
 * Не найден счет
 */
public class NotFoundUser extends RuntimeException {
    public NotFoundUser(final String message) {
        super(message);
    }
}
