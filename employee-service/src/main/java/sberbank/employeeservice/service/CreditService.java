package sberbank.employeeservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sberbank.employeeservice.domain.dto.Credit;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CreditService {
    public List<Credit> getCreditList() {
        return Arrays.asList(new Credit(1L, "Name1", 10), new Credit(2L, "Name2", 12));
    }
    public Credit createCredit(Credit credit) {
        return credit;
    }
}
