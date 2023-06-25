package bitlab.tech.finish.messenger.dto;
import bitlab.tech.finish.messenger.models.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatDTO {
    private Long id;
    private LocalDateTime createdAt;
    private User fromUser;
    private User toUser;
    private String message;
    private String file;


}
