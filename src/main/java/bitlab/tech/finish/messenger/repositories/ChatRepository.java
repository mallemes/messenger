package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByFromUserAndToUserOrToUserAndFromUserOrderByCreatedAt(User fromUser, User toUser, User fromUser2, User toUser2);
}
