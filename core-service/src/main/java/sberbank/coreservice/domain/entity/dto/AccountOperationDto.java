package sberbank.coreservice.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperationDto {
    private String type;
    private String value;
    private String executeDate;

}
