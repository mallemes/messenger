package bitlab.tech.finish.messenger.models.group_p;

import bitlab.tech.finish.messenger.models.BaseModel;
import bitlab.tech.finish.messenger.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_group_posts")
@Getter
@Setter
public class GPost extends BaseModel {

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;


    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
