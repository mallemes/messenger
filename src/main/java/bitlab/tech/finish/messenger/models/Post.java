package bitlab.tech.finish.messenger.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_user_posts")
@Getter
@Setter
public class Post extends BaseModel {

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private User user;

}
