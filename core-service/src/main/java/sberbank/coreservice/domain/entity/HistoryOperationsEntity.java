package sberbank.coreservice.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "HISTORY_OPERATIONS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOperationsEntity extends BaseEntity {

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private TypeOfOperation type;

    @Column(name = "execute_date")
    private LocalDateTime executeDate;

    @Column(name = "accountId")
    private Long accountId;
}
