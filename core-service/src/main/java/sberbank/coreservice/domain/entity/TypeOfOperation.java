package sberbank.coreservice.domain.entity;

import lombok.Getter;

public enum TypeOfOperation {
    WITHDRAWAL_OF_FUNDS_FROM_ACCOUNT("Снятие средств со счета в размере"),
    ACCOUNT_REPLENISHMENT("Пополнение счета на сумму");

    /**
     * Описание операции
     */
    @Getter
    private final String description;

    /**
     * Конструктор
     *
     * @param description описание операции
     */
    TypeOfOperation(final String description) {
        this.description = description;
    }
}
