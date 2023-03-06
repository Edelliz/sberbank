package sberbank.employeeservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryOperation {
    private String type;
    private String value;
    private String executeDate;
}
