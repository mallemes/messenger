package bitlab.tech.finish.messenger.models.group_p;


import bitlab.tech.finish.messenger.models.BaseModel;
import bitlab.tech.finish.messenger.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_groups")
@Getter
@Setter
public class Group extends BaseModel {


    @Column(name = "name")
    private String name;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String image;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<GPost> posts;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
    private List<User> users;

    public String loadImage(){
        if (image == null || image.isEmpty()) {
            return "/defaults/default-group.png";
        }
        return image;
    }
}
