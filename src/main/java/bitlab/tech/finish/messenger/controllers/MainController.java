package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.Post;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.PostService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping(value = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping(value = "/profile/{username}")
    public String profilePage(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return "redirect:/errors/404";
        }
        User authUser = userService.getCurrentSessionUser();
        if (authUser != null && authUser.getId().equals(user.getId())) {
            model.addAttribute("user", userService.getCurrentSessionUser());
            return "profile"; // auth user to auth user profile
        } else if (authUser != null) {
            model.addAttribute("user", user);
            return "profiles/auth-u-to-u-prf"; // auth user to user profile
        } else {
            model.addAttribute("user", user);
            return "profiles/anon-u-to-u-prf"; // anon user to user profile
        }

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/auth-profile/create/post")
    public String toUpdateProfile(Post post) {
        post.setUser(userService.getCurrentSessionUser());
        postService.save(post);
        return "redirect:/profile?post-success";
    }

    @PostMapping(value = "/to-update-password")
    public String toUpdatePassword(
            @RequestParam(name = "user_old_password") String oldPassword,
            @RequestParam(name = "user_new_password") String newPassword,
            @RequestParam(name = "user_repeat_new_password") String repeatNewPassword) {

        if (newPassword.equals(repeatNewPassword)) {

            User user = userService.updatePassword(newPassword, oldPassword);
            if (user != null) {
                return "redirect:/update-password-page?success";
            } else {
                return "redirect:/update-password-page?oldpassworderror";
            }
        } else {
            return "redirect:/update-password-page?passwordmismatch";
        }
    }


    // Authenticated users can access this page
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile")
    public String userProfile() {
        return "redirect:/profile/" + userService.getCurrentSessionUser().getUsername();
    }
}
