package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/chat/{username}")
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/chat/send/message/{username}")
    public String userChat(@PathVariable String username, @RequestParam(name = "message") String message) throws NoHandlerFoundException {
        User toUser = userService.getUserByUsername(username);
        User authUser = userService.getCurrentSessionUser();
        if (toUser == null) { // if user is not found in database redirect to 404 page
            throw new NoHandlerFoundException("GET", "/chat/" + username, HttpHeaders.EMPTY);
        }
        userService.sendMessage(authUser, toUser, message);
        return "redirect:/chat/" + username;
    }
    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }


    @GetMapping(value = "errors/403")
    public String accessDenied() {
        return "handlers/403-page";
    }


    @PostMapping(value = "/register")
    public String toSignUp(@RequestParam(name = "username") String email,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "repeat_password") String repeatPassword) {
        if (password.equals(repeatPassword)) {
            User user = new User();
            user.setUsername(email);
            user.setPassword(password);
            User newUser = userService.addUser(user);
            if (newUser != null) {
                return "redirect:/register?success";
            } else {
                return "redirect:/register?username_error";
            }
        } else {
            return "redirect:/register?password_error";
        }
    }





}
