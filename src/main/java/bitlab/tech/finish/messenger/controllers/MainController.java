package bitlab.tech.finish.messenger.controllers;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/chat")
    public String errorPage() {
        return "chat";
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
