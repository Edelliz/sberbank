package sberbank.employeeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sberbank.employeeservice.domain.dto.CreateCredit;
import sberbank.employeeservice.domain.dto.Credit;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URI = "http://localhost:8765/loan/rate";
    public List<Credit> getCreditList() {
        Object[] response = restTemplate.getForEntity(URI, Object[].class).getBody();
        return Arrays.stream(Objects.requireNonNull(response))
                        .map(object -> objectMapper.convertValue(object, Credit.class))
                        .collect(Collectors.toList());
    }
    public Credit createCredit(CreateCredit credit) {
        Credit response = restTemplate.postForObject(URI, credit, Credit.class);
        return response;
    }
}
