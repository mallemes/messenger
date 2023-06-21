package bitlab.tech.finish.messenger.controllers;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping(value = "/") // index page
    public String indexPage() {
        return "index";
    }


    @GetMapping(value = "/login") // login page
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register") // register page
    public String registerPage() {
        return "register";
    }


    @GetMapping(value = "errors/403") // 403 page
    public String accessDenied() {
        return "handlers/403-page";
    }


    @PostMapping(value = "/register") // register post method for creating new user
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/friends")
    public String friendsPage(Model model) {
        User auth =userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        model.addAttribute("friends" , auth.getRelatedUsers());
        return "auth_user_templates/friends";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/groups")
    public String groupsPage() {
        return "auth_user_templates/groups";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/friends/add")
    public String addFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth =userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().add(friend);
        userService.saveUser(auth);
        return "redirect:/friends";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/friends/remove")
    public String removeFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth =userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().remove(friend);
        userService.saveUser(auth);
        return "redirect:/friends";
    }
}
