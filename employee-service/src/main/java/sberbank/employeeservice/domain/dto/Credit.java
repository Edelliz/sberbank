package sberbank.employeeservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private Long id;
    private String name;
    private Integer rate;
    private User employee;
}