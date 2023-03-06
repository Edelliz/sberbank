package sberbank.loanservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sberbank.loanservice.domain.dto.AccountAmountUpdateDto;
import sberbank.loanservice.domain.dto.AccountDto;
import sberbank.loanservice.domain.dto.UserDto;

@Service
class CommonService {
    private final RestTemplate restTemplate = new RestTemplate();

    private final String URI_TO_USER = "http://localhost:8765/user/";
    private final String URI_TO_CORE = "http://localhost:8765/core/";

    public UserDto getUser(Long userId) {

        return restTemplate.getForEntity(URI_TO_USER + userId, UserDto.class).getBody();
    }

    public AccountDto withdrawAccount(Long accountId, Double amount) {

        return restTemplate.postForEntity(URI_TO_CORE + "account/withdrawal",
                new AccountAmountUpdateDto(accountId, amount),
                AccountDto.class
        ).getBody();
    }

    public AccountDto refillAccount(Long accountId, Double amount) {

        return restTemplate.postForEntity(URI_TO_CORE + "account/refill",
                new AccountAmountUpdateDto(accountId, amount),
                AccountDto.class
        ).getBody();
    }
}
