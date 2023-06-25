package bitlab.tech.finish.messenger.services;

import bitlab.tech.finish.messenger.models.Post;
import bitlab.tech.finish.messenger.models.group_p.GPost;
import bitlab.tech.finish.messenger.repositories.GPostRepository;
import bitlab.tech.finish.messenger.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private GPostRepository gPostRepository;



    public void deletePostById(Long id) {
        postRepository.deletePostById(id);
    }

    public List<GPost> allGroupPosts() {
        return gPostRepository.findAll();
    }
    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public GPost saveGroupPost(GPost post) {
        return gPostRepository.save(post);
    }

    public GPost findGroupPostById(Long id) {
        return gPostRepository.findById(id).orElse(null);
    }
    public void deleteGroupPost(GPost post) {
        gPostRepository.deleteById(post.getId());
    }

}
