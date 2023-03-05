package sberbank.coreservice.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * Базовая сущность
 */
@MappedSuperclass
@Getter
@EqualsAndHashCode
public class BaseEntity {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq")
    @SequenceGenerator(name = "app_seq", sequenceName = "app_seq", initialValue = 10_000)
    private Long id;
}