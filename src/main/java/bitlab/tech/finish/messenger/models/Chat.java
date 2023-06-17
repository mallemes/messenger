package bitlab.tech.finish.messenger.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "t_user_chats")
@Getter
@Setter
public class Chat extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "image")
    private String image;
}
