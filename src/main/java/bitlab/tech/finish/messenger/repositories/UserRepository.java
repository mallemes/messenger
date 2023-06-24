package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.Chat;
import bitlab.tech.finish.messenger.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);

    User findByUsernameOrEmail(String username, String email);

    Set<User> findAllByRelatedUsersContains(User user);

    List<User> searchAllByUsernameContainsOrFirstNameContainsOrLastNameContains(String username, String firstName, String lastName);
    List<User> searchAllByUsernameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String username, String lastName, String firstName);

}
