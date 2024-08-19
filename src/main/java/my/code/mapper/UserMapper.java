package my.code.mapper;

import my.code.database.entity.User;
import my.code.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto map(User user);

    @InheritInverseConfiguration
    User map(UserDto userDto);
}
