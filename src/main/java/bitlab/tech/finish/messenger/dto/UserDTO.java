package bitlab.tech.finish.messenger.dto;
import bitlab.tech.finish.messenger.models.Post;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.models.group_p.Group;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String job;
    private String avatar;
    private String phoneNumber;
    private String bio;
}
