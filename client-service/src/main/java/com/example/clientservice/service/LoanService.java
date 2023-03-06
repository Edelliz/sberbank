package com.example.clientservice.service;

import com.example.clientservice.dto.LoanCreateDto;
import com.example.clientservice.dto.LoanDto;
import com.example.clientservice.dto.LoanRepayDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URI = "http://localhost:8765/loan";

    public LoanDto createLoan(LoanCreateDto dto) {

        return restTemplate.postForEntity(URI, dto, LoanDto.class).getBody();
    }

    public LoanDto getLoan(Long loanId) {

        return restTemplate.getForEntity(URI + "/" + loanId, LoanDto.class).getBody();
    }

    public List<LoanDto> getLoans(Long clientId) {
        Object[] response = restTemplate.getForEntity(URI + "/" + clientId + "/client", Object[].class).getBody();

        return Arrays.stream(Objects.requireNonNull(response))
                .map(object -> objectMapper.convertValue(object, LoanDto.class))
                .collect(Collectors.toList());
    }

    public LoanDto repayLoan(LoanRepayDto dto) {

        return restTemplate.postForEntity(URI + "/repay", dto, LoanDto.class).getBody();
    }
}
