package sberbank.coreservice.domain.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Account")
@Entity
@Data
public class AccountEntity extends BaseEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "amount")
    private Long amount;

}
