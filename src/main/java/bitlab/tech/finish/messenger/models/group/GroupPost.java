package bitlab.tech.finish.messenger.models.group;

import bitlab.tech.finish.messenger.models.BaseModel;
import bitlab.tech.finish.messenger.models.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_group_posts")
public class GroupPost extends BaseModel {

    @Column(name = "text")
    private String text;

    @Column(name = "image")
    private String image;


    @ManyToOne
    private User author;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
