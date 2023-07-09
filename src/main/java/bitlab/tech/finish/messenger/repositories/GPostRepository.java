package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.group_p.GPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface GPostRepository extends JpaRepository<GPost, Long> {

    @Modifying
    @Query("DELETE FROM GPost WHERE id = ?1")
    void deleteGPostById(Long id);

}

