package my.code.clinet.sevice;

import lombok.RequiredArgsConstructor;
import my.code.clinet.dto.UserClientDto;
import my.code.clinet.feign.UserClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;

    public List<UserClientDto> getAllUsers() {
        return userClient.getAllUsers();
    }


    public UserClientDto getUser(Long id) {
        return userClient.getUser(id);
    }

}
