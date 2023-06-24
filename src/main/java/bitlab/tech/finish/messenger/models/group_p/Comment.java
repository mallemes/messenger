package bitlab.tech.finish.messenger.models.group_p;


import bitlab.tech.finish.messenger.models.BaseModel;
import bitlab.tech.finish.messenger.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_comments")
public class Comment extends BaseModel {

    @Column(name = "text")
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private GPost post;

}
