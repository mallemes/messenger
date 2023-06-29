package bitlab.tech.finish.messenger.controllers.RestControllers;

import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.mapper.UserMapper;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.ChatService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/chat")
public class ChatRestController {

    private final ChatService chatService;
    private final UserService userService;
    private final UserMapper userMapper;



    @GetMapping(value = "/{username}")
    public ResponseEntity<ChatResponse> getUserChat(@PathVariable String username) {
        UserDTO toUserDTO = userService.getUserByUsernameDTO(username);
        UserDTO authUserDTO = userMapper.toUserDTO(userService.getUserByUsername(userService.getCurrentSessionUser().getUsername()));
        if (toUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        List<ChatDTO> chatResponse = userService.userChatDTO(authUserDTO, toUserDTO);
        ChatResponse response = new ChatResponse(chatResponse, authUserDTO.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/send/message/{username}")
    public ResponseEntity<Void> sendMessage2(@PathVariable String username, @RequestParam(name = "message") String message) {
        User toUser = userService.getUserByUsername(username);
        User authUser = userService.getCurrentSessionUser();
        if (toUser == null) {
            return ResponseEntity.notFound().build();
        }
        chatService.sendMessage(authUser, toUser, message);
        return ResponseEntity.ok().build();
    }


}
@Getter
@Setter
 class ChatResponse {
    private List<ChatDTO> chats;
    private Long currentUserId;

    public ChatResponse(List<ChatDTO> chats, Long id) {
        this.chats = chats;
        this.currentUserId = id;
    }

    public List<ChatDTO> getChats() {
        return chats;
    }

}
