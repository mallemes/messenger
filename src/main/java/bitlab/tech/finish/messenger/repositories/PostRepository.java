package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public Post findPostById(Long id);

    public void deletePostById(Long id);




}
