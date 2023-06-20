package bitlab.tech.finish.messenger.services;


import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public void sendMessage(User fromUser, User toUser, String message) {
        Chat chat = new Chat();
        chat.setFromUser(fromUser);
        chat.setToUser(toUser);
        chat.setMessage(message);
        chatRepository.save(chat);
    }
}
