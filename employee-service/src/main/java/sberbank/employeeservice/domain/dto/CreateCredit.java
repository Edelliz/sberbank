package sberbank.employeeservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCredit {
    private Long employeeId;
    private String name;
    private Integer rate;
}
