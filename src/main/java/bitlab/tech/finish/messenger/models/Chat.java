package bitlab.tech.finish.messenger.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "t_user_chats")
@Getter
@Setter
@ToString
public class Chat extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    @JsonIgnore
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    @JsonIgnore
    private User toUser;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "image")
    private String image;
}
