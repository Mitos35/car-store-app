package my.code.core.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.UserRequestDto;
import my.code.core.application.dto.UserResponseDto;
import my.code.core.application.dto.UserUpdateRequestDto;
import my.code.core.database.entity.User;
import my.code.core.database.repository.UserRepository;
import my.code.core.domain.model.UserModel;
import my.code.core.domain.service.UserService;
import my.code.core.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserModel> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toModelFromEntity);
    }

    @Override
    @Transactional
    public UserModel createUser(UserRequestDto userRequestDto) {
        return userMapper.toModelFromEntity(
                userRepository.save(userMapper.toEntityFromUserRequest(userRequestDto))
        );
    }

    @Override
    @Transactional
    public UserModel updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        return userRepository.findById(userUpdateRequestDto.getId())
                .map(user -> {
                    userMapper.updateUserFromDto(userUpdateRequestDto, user);
                    User updatedUser = userRepository.save(user);
                    return userMapper.toModelFromEntity(updatedUser);
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userUpdateRequestDto.getId()));
    }

    @Override
    public UserModel deleteUser(UserResponseDto userResponseDto) {
        Long userId = userResponseDto.getId();

        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return userMapper.toModelFromUserRequest(userResponseDto);
        } else {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

    }
}
