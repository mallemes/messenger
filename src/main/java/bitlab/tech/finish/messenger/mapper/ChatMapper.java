package bitlab.tech.finish.messenger.mapper;


import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.models.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(source = "image", target = "file")
    ChatDTO toChatDTO(Chat chat);

    @Mapping(source = "file", target = "image")
    Chat toChat(ChatDTO chatDTO);

    List<ChatDTO> toChatDTOList(List<Chat> chats);

    List<Chat> toChatList(List<ChatDTO> chatDTOs);
}
