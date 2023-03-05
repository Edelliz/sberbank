package com.example.clientservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sberbank.coreservicecommon.dto.AccountOperationDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HistoryOperationsService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String URI = "http://localhost:8765/core/history/operations/";

    @SneakyThrows
    public List<AccountOperationDto> getAccountHistory(Long accountId) {
        Object[] response = restTemplate.getForEntity(URI + accountId, Object[].class).getBody();
        return Arrays.stream(Objects.requireNonNull(response))
                .map(object -> objectMapper.convertValue(object, AccountOperationDto.class))
                .collect(Collectors.toList());

    }
}
