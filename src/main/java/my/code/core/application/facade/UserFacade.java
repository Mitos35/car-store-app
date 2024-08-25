package my.code.core.application.facade;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.UserDto;
import my.code.core.application.dto.UserRequestDto;
import my.code.core.application.dto.UserResponseDto;
import my.code.core.application.dto.UserUpdateRequestDto;
import my.code.core.application.exceptions.NotFoundException;
import my.code.core.domain.service.UserService;
import my.code.core.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public List<UserResponseDto> getUsers() {
        return userService.getUsers()
                .stream()
                .map(userMapper::toModelFromUserResponse)
                .toList();
    }

    public UserResponseDto getUserById(Long id) {
        return userService.getUserById(id).map(userMapper::toModelFromUserResponse)
                .orElseThrow(() -> new NotFoundException("User with id {0} not found", id));
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return userMapper.toModelFromUserResponse(userService.createUser(userRequestDto));
    }

    public UserResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        return userMapper.toModelFromUserResponse(userService.updateUser(userUpdateRequestDto));
    }

    public UserResponseDto addUser(UserRequestDto requestDto) {
        return userMapper.toModelFromUserResponse(userService.createUser(requestDto));
    }

    public UserResponseDto deleteUser(Long id) {
        UserResponseDto userById = getUserById(id);
        return userMapper.toModelFromUserResponse(userService.deleteUser(userById));
    }
}
