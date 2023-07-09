package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.ChatService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatController {

    private final UserService userService;
    private final ChatService chatService;


    // chat page for current user and toUser
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "{username}")
    public String userChat(@PathVariable String username, Model model) throws NoHandlerFoundException {
        User toUser = userService.getUserByUsername(username);
        User authUser = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        if (toUser == null) { // if user is not found in database redirect to 404 page
            throw new NoHandlerFoundException("GET", "/chat/" + username, HttpHeaders.EMPTY);
        }
        model.addAttribute("toUser", toUser);
        model.addAttribute("fromUser", authUser);
        model.addAttribute("chats", userService.userChat(authUser, toUser));
        return "chat";
    }

    // chat send message from current user to toUser
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/send/message/{username}")
    public String userChat(@PathVariable String username, @RequestParam(name = "message") String message) throws NoHandlerFoundException {
        User toUser = userService.getUserByUsername(username);
        User authUser = userService.getCurrentSessionUser();
        if (toUser == null) { // if user is not found in database redirect to 404 page
            throw new NoHandlerFoundException("GET", "/chat/" + username, HttpHeaders.EMPTY);
        }
        chatService.sendMessage(authUser, toUser, message);
        return "redirect:/chat/" + username;
    }

}
