package bitlab.tech.finish.messenger.mapper;
import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);


    User toEntityUser(UserDTO userDTO);

    List<ChatDTO> toUserDTOList(List<User> users);

    List<Chat> toEntityUserList(List<UserDTO> userDTOList);
}
