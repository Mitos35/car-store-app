package my.code.clinet.feign;

import my.code.clinet.dto.UserClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "userapi", url = "${client.userapi.url}")
public interface UserClient {

    @GetMapping("/users")
    List<UserClientDto> getAllUsers();

    @GetMapping("/users/{id}")
    UserClientDto getUser(@PathVariable(name = "id") long id);

}
