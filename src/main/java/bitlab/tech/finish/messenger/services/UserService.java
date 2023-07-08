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

    @Override // load user by username method for spring security
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
        User user = userRepository.findByUsername(username);
        if (user == null)  // if user not found throw exception
            throw new UsernameNotFoundException("User not found");

        if (user.isBanned()) {// if user is banned throw exception
            throw new UsernameNotFoundException("User is banned");
        }
        return user;
    }

    public User saveUser(User user) {   // save user method using for update user
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user) { // add user method
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

    public User updatePassword(String newPassword, String oldPassword) { // update password method
        User currentUser = getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public User getCurrentSessionUser() { // get current (session/authenticated) user method
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    public List<Chat> userChat(User from, User to) { // get user chat method
        return chatRepository.findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(from, to, from, to);
    }


    public List<User> searchUsers(String query) { // search users by query method
        return userRepository
                .searchAllByUsernameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(
                        query, query, query);
    }




    // dto mapper methods
    public List<ChatDTO> userChatDTO(UserDTO from, UserDTO to) { // get user chat dto mapper method
        User fromUser = userMapper.toEntityUser(from);
        User toUser = userMapper.toEntityUser(to);
        return chatMapper.toChatDTOList(chatRepository.findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(fromUser, toUser, fromUser, toUser));
    }

    public UserDTO getUserByUsernameDTO(String username) { // get user by username dto mapper method
        return userMapper.toUserDTO(userRepository.findByUsername(username));
    }

    public List<UserDTO> userListDTO() { // get all users list dto mapper method for admin panel
        return userMapper.toUserDTOList(userRepository.findAll().stream().toList());
    }

    public void toggleBan(Long id, boolean banned) { //  ban or unban user by id method for admin panel
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setBanned(banned);
            userRepository.save(user);
        }
    }

}
