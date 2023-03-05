package sberbank.userservice.domain.enums;

import lombok.Getter;

public enum UserStatus {
    ACTIVE("Активен"),
    BLOCK("Заблокирован");

    /**
     * Описание операции
     */
    @Getter
    private final String description;

    /**
     * Конструктор
     *
     * @param description описание статуса
     */
    UserStatus(final String description) {
        this.description = description;
    }
}
