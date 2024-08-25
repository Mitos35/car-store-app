package my.code.core.domain.service;

import my.code.core.application.dto.UserRequestDto;
import my.code.core.application.dto.UserResponseDto;
import my.code.core.application.dto.UserUpdateRequestDto;
import my.code.core.database.entity.User;
import my.code.core.application.dto.UserDto;
import my.code.core.domain.model.UserModel;
import my.code.core.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getUsers();

    Optional<UserModel> getUserById(Long id);

    UserModel createUser(UserRequestDto userRequestDto);

    UserModel updateUser(UserUpdateRequestDto userUpdateRequestDto);

    UserModel deleteUser(UserResponseDto userResponseDto);
}
