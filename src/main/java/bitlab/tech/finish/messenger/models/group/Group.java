package bitlab.tech.finish.messenger.models.group;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "poster")
    private String image;

    @OneToMany(mappedBy = "group")
    private List<GroupPost> groupUsers;
}
