package sberbank.employeeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sberbank.employeeservice.domain.dto.HistoryOperation;
import sberbank.employeeservice.domain.dto.Account;
import sberbank.employeeservice.domain.dto.ClientsAccounts;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URI = "http://localhost:8765/core/account/";
    private final String URIHistory = "http://localhost:8765/core/history/operations/";
    private final String URIUser = "http://localhost:8765/user/";
    public ClientsAccounts getUsersAccountsList(int userId) {
        Object[] response = restTemplate.getForEntity(URI + userId + "/client", Object[].class).getBody();
        String responseUser = restTemplate.getForObject(URIUser + userId + "/fullname", String.class);
        return new ClientsAccounts(responseUser,
                Arrays.stream(Objects.requireNonNull(response))
                .map(object -> objectMapper.convertValue(object, Account.class))
                .collect(Collectors.toList()));
    }
    public List<HistoryOperation> getAccountHistory(int accountId) {
        Object[] response = restTemplate.getForEntity(URIHistory + accountId, Object[].class).getBody();
        return Arrays.stream(Objects.requireNonNull(response))
                .map(object -> objectMapper.convertValue(object, HistoryOperation.class))
                .collect(Collectors.toList());
    }
}
