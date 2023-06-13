package bitlab.tech.finish.messenger.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "t_user_posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    private User user;

    @Column(name = "title", nullable = true)
    private String message;

    @Column(name = "date", nullable = true)
    private Date date;

    @Column(name = "text", nullable = true)
    private String text;

    @Column(name = "image", nullable = true)
    private String image;
}
