package bitlab.tech.finish.messenger.repositories;
import bitlab.tech.finish.messenger.models.User;
import bitlab.tech.finish.messenger.models.group_p.Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Transactional
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> searchAllByNameContainingIgnoreCase(String name);

    Group findBySlug(String name);

}
