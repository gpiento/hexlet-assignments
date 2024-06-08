package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> getPosts() {
        return postRepository.findAll().stream().map(this::postToDto).toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO getPost(@PathVariable long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return postToDto(post);
    }

    private PostDTO postToDto(Post post) {
        List<CommentDTO> comments = commentRepository.findByPostId(post.getId()).stream()
                .map(this::commentToDto)
                .toList();
        return new PostDTO(post.getId(), post.getTitle(), post.getBody(), comments);
    }

    private CommentDTO commentToDto(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getBody());
    }
}
// END
