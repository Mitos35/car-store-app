package my.code.core.application.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.UserDto;
import my.code.core.application.dto.UserRequestDto;
import my.code.core.application.dto.UserResponseDto;
import my.code.core.application.dto.UserUpdateRequestDto;
import my.code.core.application.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Users operations in the car store", description = "CRUD Operations for working with Users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAllUsers() {
        return userFacade.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUser(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto aadUser(@RequestBody UserRequestDto userRequestDto) {
        return userFacade.addUser(userRequestDto);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUser(@PathVariable() Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return userFacade.updateUser(userUpdateRequestDto);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserResponseDto deleteUser (@PathVariable Long id){
        return userFacade.deleteUser(id);
    }


}
