package bitlab.tech.finish.messenger.services;


import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.mapper.ChatMapper;
import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMapper chatMapper;

    public void sendMessage(User fromUser, User toUser, String message) {
        Chat chat = new Chat();
        chat.setFromUser(fromUser);
        chat.setToUser(toUser);
        chat.setMessage(message);
        chatRepository.save(chat);
    }
//    public ChatDTO sendMessageDTO(User fromUser, User toUser, String message) {
//        ChatDTO chatDTO = new ChatDTO();
//        chatDTO.setFromUser(fromUser);
//        chatDTO.setToUser(toUser);
//        chatDTO.setMessage(message);
//       return chatMapper.toChatDTO(chatRepository.save(chatMapper.toChat(chatDTO)));
//    }
}
