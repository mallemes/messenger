package bitlab.tech.finish.messenger.mapper;
import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    UserDTO toUserDTO(User user);


    User toEntityUser(UserDTO userDTO);

    List<UserDTO> toUserDTOList(List<User> users);

    List<User> toEntityUserList(List<UserDTO> userDTOList);
}
