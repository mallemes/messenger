package bitlab.tech.finish.messenger.services;

import bitlab.tech.finish.messenger.models.Post;
import bitlab.tech.finish.messenger.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void deletePostById(Long id) {
        postRepository.deletePostById(id);
    }

    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

}
