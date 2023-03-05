package sberbank.userservice.domain.enums;

import lombok.Getter;

public enum UserRole {
    CLIENT("Клиент"),
    EMPLOYEE("Сотрудник");

    /**
     * Описание операции
     */
    @Getter
    private final String description;

    /**
     * Конструктор
     *
     * @param description описание роли
     */
    UserRole(final String description) {
        this.description = description;
    }

}
