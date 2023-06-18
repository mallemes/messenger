package bitlab.tech.finish.messenger.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User extends BaseModel implements UserDetails  {


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "job")
    private String job;

    @Column(name = "avatar" ,columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "last_online")
    private LocalDateTime lastOnlineDate;

    @Column(name = "bio" ,columnDefinition = "TEXT")
    private String bio;



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
        return this.username;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Post> posts;
}