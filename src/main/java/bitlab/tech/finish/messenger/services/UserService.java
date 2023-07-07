package bitlab.tech.finish.messenger.services;

import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.mapper.ChatMapper;
import bitlab.tech.finish.messenger.mapper.UserMapper;
import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.Permission;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.repositories.ChatRepository;
import bitlab.tech.finish.messenger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
        User user = userRepository.findByUsername(username);
        if (user == null)  // if user not found throw exception
            throw new UsernameNotFoundException("User not found");

        if (user.isBanned()) {// if user is banned throw exception
            throw new UsernameNotFoundException("User is banned");
        }
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user) {
        User checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savingUser = userRepository.save(user);
            Permission userRolePermission = permissionService.userRolePermission();
            savingUser.getPermissions().add(userRolePermission);
            savingUser.setBanned(false);
            userRepository.save(savingUser); //save user with role
            return savingUser;
        }
        return null;
    }

    public User updatePassword(String newPassword, String oldPassword) {
        User currentUser = getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public User getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    public List<Chat> userChat(User from, User to) {
        return chatRepository.findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(from, to, from, to);
    }


    public List<User> searchUsers(String query) {
        return userRepository
                .searchAllByUsernameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(
                        query, query, query);
    }


    // dto mapper methods
    public List<ChatDTO> userChatDTO(UserDTO from, UserDTO to) {
        User fromUser = userMapper.toEntityUser(from);
        User toUser = userMapper.toEntityUser(to);
        return chatMapper.toChatDTOList(chatRepository.findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(fromUser, toUser, fromUser, toUser));
    }

    public UserDTO getUserByUsernameDTO(String username) {
        return userMapper.toUserDTO(userRepository.findByUsername(username));
    }

    public List<UserDTO> userListDTO() {
        return userMapper.toUserDTOList(userRepository.findAll().stream().toList());
    }

    public void toggleBan(Long id, boolean banned) { // toggle ban user by id
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBanned(banned);
            userRepository.save(user);
        }
    }

}
