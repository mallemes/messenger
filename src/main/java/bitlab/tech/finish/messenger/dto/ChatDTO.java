package bitlab.tech.finish.messenger.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatDTO {
    private Long id;
    private LocalDateTime createdAt;
    private UserDTO fromUser;
    private UserDTO toUser;
    private String message;
    private String file;

}
