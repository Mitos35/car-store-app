package my.code.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import my.code.database.entity.User;
import my.code.database.repository.UserRepository;
import my.code.dto.UserDto;
import my.code.mapper.UserMapper;
import my.code.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @PostConstruct
    public void init() {
        List<UserDto> users = getUsers();
        System.out.println(users);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Optional<UserDto> getUserById(Long Id) {
        return Optional.empty();
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
