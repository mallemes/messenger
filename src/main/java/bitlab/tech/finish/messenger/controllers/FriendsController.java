package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@RequestMapping(value = "/friends")
@RequiredArgsConstructor
public class FriendsController {

    private final UserService userService;

    @GetMapping(value = "/{username}") // friends page for user
    public String friendsPage(@PathVariable String username, Model model) throws NoHandlerFoundException {
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null)
            throw new NoHandlerFoundException("GET", "/friends/" + username, HttpHeaders.EMPTY);
        model.addAttribute("friends", currentUser.getRelatedUsers());
        model.addAttribute("currentUser", currentUser);
        return "list_pages/friends";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/add")
    public String addFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().add(friend);
        userService.saveUser(auth);
        return "redirect:/profile/" + friendUsername;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/remove")
    public String removeFriend(@RequestParam(name = "friend_username") String friendUsername) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        User friend = userService.getUserByUsername(friendUsername);
        auth.getRelatedUsers().remove(friend);
        userService.saveUser(auth);
        return "redirect:/profile/" + friendUsername;
    }
}
