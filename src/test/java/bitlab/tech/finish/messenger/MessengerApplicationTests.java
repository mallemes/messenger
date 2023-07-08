package bitlab.tech.finish.messenger;

import bitlab.tech.finish.messenger.dto.ChatDTO;
import bitlab.tech.finish.messenger.dto.UserDTO;
import bitlab.tech.finish.messenger.mapper.ChatMapper;
import bitlab.tech.finish.messenger.mapper.UserMapper;
import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.repositories.ChatRepository;
import bitlab.tech.finish.messenger.repositories.UserRepository;
import bitlab.tech.finish.messenger.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MessengerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void checkUserChatApi() {

        System.out.println("Hello World");
    }
    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private ChatMapper chatMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatRepository chatRepository;

    @Test
    public void testUserChatDTO() {
        // create test data
        UserDTO fromUserDTO = new UserDTO();
        UserDTO toUserDTO = new UserDTO();
        User fromUser = new User();
        User toUser = new User();
        Chat chat = new Chat();
        List<Chat> chatList = List.of(chat);

        //setup mocks
        Mockito.when(userMapper.toEntityUser(fromUserDTO)).thenReturn(fromUser);
        Mockito.when(userMapper.toEntityUser(toUserDTO)).thenReturn(toUser);
        Mockito.when(chatRepository.findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(fromUser, toUser, fromUser, toUser)).thenReturn(chatList);
        Mockito.when(chatMapper.toChatDTOList(chatList)).thenReturn(Collections.singletonList(new ChatDTO()));

        // call method
        List<ChatDTO> result = userService.userChatDTO(fromUserDTO, toUserDTO);

        // check results
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testGetUserByUsernameDTO() {
        // create test data
        String username = "testuser";
        User user = new User();
        UserDTO userDTO = new UserDTO();

        // setup mocks
        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);
        Mockito.when(userMapper.toUserDTO(user)).thenReturn(userDTO);

        // call method
        UserDTO result = userService.getUserByUsernameDTO(username);

        // check results
        Assertions.assertNotNull(result);
        Assertions.assertEquals(userDTO, result);
    }

    @Test
    public void testUserListDTO() {
        // create test data
        User user = new User();
        List<User> userList = List.of(user);
        UserDTO userDTO = new UserDTO();
        List<UserDTO> expectedList = List.of(userDTO);

        //setup mocks
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userMapper.toUserDTOList(userList)).thenReturn(expectedList);

        // call method
        List<UserDTO> result = userService.userListDTO();

        // check results
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedList.size(), result.size());
        Assertions.assertEquals(expectedList.get(0), result.get(0));
    }

    @Test
    public void testToggleBan() {
        // create test data
        Long id = 1L;
        boolean banned = true;
        User user = new User();
        user.setId(id);

        // setup mocks
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // call method
        userService.toggleBan(id, banned);

        // check results
        Assertions.assertTrue(user.isBanned());
    }

}
