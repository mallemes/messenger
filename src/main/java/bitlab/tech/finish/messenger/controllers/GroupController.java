package bitlab.tech.finish.messenger.controllers;

import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.models.group_p.GPost;
import bitlab.tech.finish.messenger.models.group_p.Group;
import bitlab.tech.finish.messenger.services.FileStorageService;
import bitlab.tech.finish.messenger.services.GroupService;
import bitlab.tech.finish.messenger.services.PostService;
import bitlab.tech.finish.messenger.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.io.IOException;

@Controller
@RequestMapping(value = "/groups")
@RequiredArgsConstructor
public class GroupController {

    @Value("${posts.images.path}")
    private String postsImagesPath;  // get path from application.properties

    @Value("${groups.images.path}")
    private String groupsImagesPath;  // get path from application.properties
    private final UserService userService;
    private final GroupService groupService;
    private final FileStorageService fileStorageService;
    private final PostService postService;

    @GetMapping(value = "/show/{slug}")
    public String showGroup(@PathVariable String slug, Model model) throws NoHandlerFoundException {
        Group group = groupService.getGroupBySlug(slug);
        if (group == null)
            throw new NoHandlerFoundException("GET", "/groups/show/" + slug, HttpHeaders.EMPTY);
        if (userService.getCurrentSessionUser() != null) {
            User authUser = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
            boolean isChecked = authUser.getGroups().stream()
                    .anyMatch(group1 -> group1.getId().equals(group.getId()));
            model.addAttribute("isChecked", !isChecked);
        }
        model.addAttribute("group", group);
        return "group/single-group";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/add")
    public String addGroup(@RequestParam(name = "group_slug") String groupSlug) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        Group group = groupService.getGroupBySlug(groupSlug);
        if (group == null)
            return "redirect:/groups/" + auth.getUsername();
        auth.getGroups().add(group);
        userService.saveUser(auth);
        return "redirect:/groups/show/" + groupSlug;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/remove")
    public String removeGroup(@RequestParam(name = "group_slug") String groupSlug) {
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        Group group = groupService.getGroupBySlug(groupSlug);
        auth.getGroups().remove(group);
        userService.saveUser(auth);
        return "redirect:/groups/show/" + groupSlug;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create")
    public String createGroup(Group group,
                              @RequestParam(name = "groupImage", required = false) MultipartFile image) throws IOException {
        if (groupService.getGroupBySlug(group.getSlug()) != null)
            return "redirect:/error?error";

        if (!image.isEmpty()){
            String fileName = fileStorageService.saveFile(image, groupsImagesPath); // save file to /resources/static/storage/groups
            group.setImage(fileName);
        }
        group.setAuthor(userService.getCurrentSessionUser());
        Group g = groupService.save(group);
        User auth = userService.getUserByUsername(userService.getCurrentSessionUser().getUsername());
        auth.getGroups().add(g);
        userService.saveUser(auth);
        return "redirect:/groups/" + auth.getUsername();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/create/post")
    public String createPost(
            @RequestParam(name = "postText") String text,
            @RequestParam(name = "postImage", required = false) MultipartFile image,
            @RequestParam(name = "groupSlug") String groupSlug) {
        try {
            Group group = groupService.getGroupBySlug(groupSlug);
            GPost post = new GPost();
            post.setText(text);
            if (!image.isEmpty() ) {
                String fileName = fileStorageService.saveFile(image, postsImagesPath); // save file to /resources/static/storage/posts
                post.setImage(fileName);
            }
            post.setAuthor(userService.getCurrentSessionUser());
            post.setGroup(group);
            postService.saveGroupPost(post);
            return "redirect:/groups/show/" + groupSlug + "?post-created";
        } catch (IOException e) {
            return "redirect:/error?error";
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/update/{currentSlug}")
    public String updateGroup(
            @PathVariable String currentSlug,
            @RequestParam(name = "slug") String slug,
            @RequestParam(name = "image") MultipartFile image,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description") String description) throws IOException {
        Group group = groupService.getGroupBySlug(currentSlug);
        if (group == null) // if group not found
            return "redirect:/error?error";
        if (!slug.equals(currentSlug)) { // if slug is changed check if slug is already taken
            Group groupBySlug = groupService.getGroupBySlug(slug);
            if (groupBySlug != null)
                return "redirect:/error?error";
            group.setSlug(slug);
        }
        group.setName(name);
        group.setStatus(status);
        if (!image.isEmpty()) { // if image is changed save new image
            String fileName = fileStorageService.saveFile(image, groupsImagesPath); // save file to /resources/static/storage/groups
            group.setImage(fileName);
        }
        group.setDescription(description);
        groupService.save(group);
        return "redirect:/groups/show/" + slug + "?group-updated";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/delete/post")
    public String deletePost(@RequestParam(name = "postId") Long postId,
                             @RequestParam(name = "groupSlug") String groupSlug) {
        GPost post = postService.findGroupPostById(postId);
        if (post == null)
            return "redirect:/error?error";
        postService.deleteGroupPost(post);
        return "redirect:/groups/show/" + groupSlug + "?post-deleted";
    }

    @GetMapping(value = "/{slug}/users") //group members page
    public String groupUsers(@PathVariable String slug, Model model) throws NoHandlerFoundException {
        Group group = groupService.getGroupBySlug(slug);
        if (group == null)
            throw new NoHandlerFoundException("GET", "/groups/" + slug + "/users", HttpHeaders.EMPTY);
        model.addAttribute("users", group.getUsers());
        model.addAttribute("group", group);
        return "group/group-users";
    }

    @GetMapping(value = "{username}") // groups page for user
    public String groupsPage(@PathVariable String username, Model model) throws NoHandlerFoundException {
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null)
            throw new NoHandlerFoundException("GET", "/groups/" + username, HttpHeaders.EMPTY);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("groups", currentUser.getGroups());
        return "group/groups";
    }


}
