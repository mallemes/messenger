package bitlab.tech.finish.messenger.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_user_posts")
@Getter
@Setter
public class Post extends BaseModel {

    @Column(name = "title")
    private String message;

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private User user;
}
