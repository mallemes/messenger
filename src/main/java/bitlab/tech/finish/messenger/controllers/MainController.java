package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.models.group_p.GPost;
import bitlab.tech.finish.messenger.services.GroupService;
import bitlab.tech.finish.messenger.services.PermissionService;
import bitlab.tech.finish.messenger.services.PostService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final GroupService groupService;
    private final PostService postService;



    @GetMapping(value = "/") // index page
    public String indexPage(Model model) {
       List<GPost>allPosts = postService.allGroupPosts();
        if (CollectionUtils.isNotEmpty(allPosts)) {
            // Shuffle the list of posts
            Collections.shuffle(allPosts);
            // Get the first 5 random posts
            List<GPost> randomPosts = ListUtils.partition(allPosts, 5).get(0);
            model.addAttribute("posts", randomPosts);
        }

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

    @GetMapping(value = "/search")
    public String searchUsers(@RequestParam(name = "query", required = false) String query,
                              @RequestParam(name = "users", required = false) String users,
                              @RequestParam(name = "groups", required = false) String groups,
                              Model model) {
        if (query != null && !query.isEmpty()) {
            model.addAttribute("users", userService.searchUsers(query));
            model.addAttribute("groups", groupService.searchGroups(query));
            model.addAttribute("query", query);
            return "list_pages/search";
        }
        if ((users != null && !users.isEmpty()) && (groups != null && !groups.isEmpty())) {
            model.addAttribute("users", userService.searchUsers(users));
            model.addAttribute("groups", groupService.searchGroups(groups));
            model.addAttribute("query", users + " " + groups);
            return "list_pages/search";
        }
        if (groups != null && !groups.isEmpty()) {
            model.addAttribute("groups", groupService.searchGroups(groups));
            model.addAttribute("query", groups);
            return "list_pages/search";
        }
        if (users != null && !users.isEmpty())
            model.addAttribute("users", userService.searchUsers(users));
        model.addAttribute("query", users);
        return "list_pages/search";
    }

    @GetMapping(value = "/friends/{username}") // friends page for user
    public String friendsPage(@PathVariable String username, Model model) throws NoHandlerFoundException {
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null)
            throw new NoHandlerFoundException("GET", "/friends/" + username, HttpHeaders.EMPTY);
        model.addAttribute("friends", currentUser.getRelatedUsers());
        model.addAttribute("currentUser", currentUser);
        return "list_pages/friends";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/friends/add")
    public String addFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().add(friend);
        userService.saveUser(auth);
        return "redirect:/profile/" + friendUsername;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/friends/remove")
    public String removeFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().remove(friend);
        userService.saveUser(auth);
        return "redirect:/profile/" + friendUsername;
    }
}
