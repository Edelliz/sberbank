package com.example.clientservice.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Data
public class AccountService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URI = "http://localhost:8765/core/account/";


    /**
     * Открытие счета
     */
    public String createAccount(Long clientId) {
        return restTemplate.postForObject(URI + clientId, null, String.class);
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
    public Long replenishAccount(Long accountId, Long amount) {
        return restTemplate.postForObject(URI + "refill/" + accountId + "/" + amount, null, Long.class);
    }

    /**
     * Снятие счета
     */
    public Long withdrawAccount(Long accountId, Long amount) {
        return restTemplate.postForObject(URI + "withdrawal/" + accountId + "/" + amount, null, Long.class);
    }

}
