package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

     Post findPostById(Long id);

    @Modifying
    @Query("DELETE FROM Post WHERE id = ?1")
     void deletePostById(Long id);




}
