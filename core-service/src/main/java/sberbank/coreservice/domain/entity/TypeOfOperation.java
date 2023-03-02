package sberbank.coreservice.domain.entity;

import lombok.Getter;

public enum TypeOfOperation {
    OPENING_AN_ACCOUNT("Открытие счета %s"),
    CLOSING_AN_ACCOUNT("Закрытие счета %s"),
    WITHDRAWAL_OF_FUNDS_FROM_ACCOUNT("Снятие средств со счета в размере %s"),
    ACCOUNT_REPLENISHMENT("Пополнение счета на сумму %s");

    /**
     * Описание операции
     */
    @Getter
    private final String description;

    /**
     * Конструктор
     *
     * @param description описание ошибки
     */
    TypeOfOperation(final String description) {
        this.description = description;
    }
}
