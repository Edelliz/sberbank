package sberbank.employeeservice.service;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sberbank.employeeservice.domain.dto.BlockClient;
import sberbank.employeeservice.domain.dto.ListUsers;
import sberbank.employeeservice.domain.dto.User;
import sberbank.employeeservice.domain.dto.UserRegister;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String URI = "http://localhost:8765/user/";
    public ListUsers getUsersList() {
        Object[] responseClient = restTemplate.getForEntity(URI + "clients", Object[].class).getBody();
        Object[] responseEmployees = restTemplate.getForEntity(URI + "employees", Object[].class).getBody();
        return new ListUsers(Arrays.stream(Objects.requireNonNull(responseClient))
                .map(object -> objectMapper.convertValue(object, User.class))
                .collect(Collectors.toList()),
                Arrays.stream(Objects.requireNonNull(responseEmployees))
                        .map(object -> objectMapper.convertValue(object, User.class))
                        .collect(Collectors.toList()));
    }
    public int getUsersRole() {
        return 1;
    }
    public void createUser(UserRegister user) {
        restTemplate.postForObject(URI + "auth/register", user, Void.class);
    }
    public void blockUser(BlockClient request) {
        restTemplate.postForLocation(URI + request.getEmployeeId() + "/" + request.getClientId() + "/block", null);
    }
    public void unblockUser(BlockClient request) {
        restTemplate.postForLocation(URI + request.getEmployeeId() + "/" + request.getClientId() + "/unblock", null);
    }
}
