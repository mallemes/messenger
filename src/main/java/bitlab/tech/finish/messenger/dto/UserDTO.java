package bitlab.tech.finish.messenger.dto;
import bitlab.tech.finish.messenger.models.Post;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.models.group_p.Group;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String job;
    private String avatar;
    private String phoneNumber;
    private String bio;
    private boolean isBanned;

    public String getCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        long seconds = ChronoUnit.SECONDS.between(createdAt, now);

        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (seconds < 3600) {
            long minutes = ChronoUnit.MINUTES.between(createdAt, now);
            return minutes + " minutes ago";
        } else if (seconds < 86400) {
            long hours = ChronoUnit.HOURS.between(createdAt, now);
            return hours + " hours ago";
        } else {
            long days = ChronoUnit.DAYS.between(createdAt, now);
            return days + " days ago";
        }
    }
    public String getAvatar() {
        if (avatar == null) {
            return "/defaults/default-user.png";
        }
        return avatar;
    }
}
