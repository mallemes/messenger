package bitlab.tech.finish.messenger.controllers.RestControllers;
import bitlab.tech.finish.messenger.services.ChatService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/chat")
public class ChatRestController {

    private final ChatService chatService;
    private final UserService userService;

//    @GetMapping
//    public String getChat() {
//        return chatService.getChat();
//    }

}
