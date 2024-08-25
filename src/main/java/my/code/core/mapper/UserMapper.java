package my.code.core.mapper;

import lombok.RequiredArgsConstructor;
import my.code.core.application.dto.UserRequestDto;
import my.code.core.application.dto.UserResponseDto;
import my.code.core.application.dto.UserUpdateRequestDto;
import my.code.core.database.entity.User;
import my.code.core.domain.model.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserModel toModelFromEntity(User user) {
        return Objects.isNull(user) ? null : mapper.map(user, UserModel.class);
    }

    public User toEntityFromUserRequest(UserRequestDto userRequestDto) {
        return Objects.isNull(userRequestDto) ? null : mapper.map(userRequestDto, User.class);
    }

    public UserModel toModelFromUserRequest(UserResponseDto userRequestDto) {
        return Objects.isNull(userRequestDto) ? null : mapper.map(userRequestDto, UserModel.class);
    }

    public UserResponseDto toModelFromUserResponse(UserModel userModel) {
        return Objects.isNull(userModel) ? null : mapper.map(userModel, UserResponseDto.class);
    }

    public void updateUserFromDto(UserUpdateRequestDto userUpdateRequestDto, User user) {
        mapper.map(userUpdateRequestDto, user);
    }
}
