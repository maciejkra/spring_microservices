package pl.jsystems.micro.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jsystems.micro.model.Post;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.model.dtos.PostDto;
import pl.jsystems.micro.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public void addPost(PostDto postDto, User author){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCategory(postDto.getCategory());
        post.setAuthor(author);
        postRepository.save(post);
    }
    public List<Object[]> getCategoryStatistics(){
        return postRepository.getCategoryStatistics();
    }
}
