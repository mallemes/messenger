package bitlab.tech.finish.messenger.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist  // This annotation is used to execute a method before persisting an entity.
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }


    public String getCreatedAtFormatted() {
        return createdAt.getDayOfMonth() + "." + createdAt.getMonthValue() + "." + createdAt.getYear();
    }

    public String difForHumans(){
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
}
