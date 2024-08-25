package my.code.clinet.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.code.clinet.dto.UserClientDto;
import my.code.clinet.sevice.UserClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Users operations in the User Client Controller", description = "Get operations for working with Users by Feign")
@RestController
@RequestMapping("/api/feign/users")
@RequiredArgsConstructor
public class UserClientController {

    private final UserClientService userClientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserClientDto> getAllUsers() {
        return userClientService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserClientDto getUser(@PathVariable Long id) {
        return userClientService.getUser(id);
    }

}
