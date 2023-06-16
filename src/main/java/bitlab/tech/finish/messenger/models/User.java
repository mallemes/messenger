package bitlab.tech.finish.messenger.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "job")
    private String job;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_online")
    private Date lastOnlineDate;

    @Column(name = "bio" )
    private String bio;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email == null ? this.username : this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @ManyToMany
    @JoinTable(
            name = "t_friends", // Название таблицы связи
            joinColumns = @JoinColumn(name = "user_id"), // Колонка, связанная с текущим пользователем
            inverseJoinColumns = @JoinColumn(name = "friend_id") // Колонка, связанная с связанным пользователем
    )
    private Set<User> relatedUsers = new HashSet<>();

    @OneToMany(mappedBy = "fromUser")
    private List<Chat> fromChats;

    @OneToMany(mappedBy = "toUser")
    private List<Chat> toChats;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}