package bitlab.tech.finish.messenger.models.group;


import bitlab.tech.finish.messenger.models.BaseModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_groups")
public class Group extends BaseModel {


    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String image;

    @OneToMany(mappedBy = "group")
    private List<GroupPost> groupUsers;
}
