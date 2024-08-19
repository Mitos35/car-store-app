package my.code.service;

import my.code.database.entity.User;
import my.code.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getUsers();

    Optional<UserDto> getUserById(Long Id);

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);
}
