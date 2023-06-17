package bitlab.tech.finish.messenger.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

}
