package com.example.clientservice.service;

import com.example.clientservice.dto.AccountAmountUpdateDto;
import com.example.clientservice.dto.AccountDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class AccountService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URI = "http://localhost:8765/core/account/";

    /**
     * Получение счета пользователя
     */
    public List<AccountDto> getAccount(Long clientId) {
        Object[] response = restTemplate.getForEntity(URI + clientId, Object[].class).getBody();
        return Arrays.stream(Objects.requireNonNull(response))
                .map(object -> objectMapper.convertValue(object, AccountDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Открытие счета
     */
    public AccountDto createAccount(Long clientId) {
        return restTemplate.postForEntity(
                URI + clientId,
                null,
                AccountDto.class
        ).getBody();
    }

    /**
     * Закрытие счета
     */
    public void deleteAccount(Long accountId) {
        restTemplate.delete(URI + accountId);
    }

    /**
     * Пополнение счета
     */
    public AccountDto replenishAccount(AccountAmountUpdateDto dto) {
        return restTemplate.postForEntity(
                URI + "refill",
                dto,
                AccountDto.class
        ).getBody();
    }

    /**
     * Снятие счета
     */
    public AccountDto withdrawAccount(AccountAmountUpdateDto dto) {
        return restTemplate.postForEntity(
                URI + "withdrawal",
                dto,
                AccountDto.class
        ).getBody();
    }

}
