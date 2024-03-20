package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit) {
        return postRepository.findAll().stream().limit(limit).toList();
    }

    @PostMapping("")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post createdPost = postRepository.save(post);
        return ResponseEntity.created(URI.create("/posts/" + createdPost.getId())).body(createdPost);
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable String id) {
        return postRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePage = postRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException(id + " Not Found"));
        maybePage.setTitle(data.getTitle());
        maybePage.setBody(data.getBody());
        maybePage.setBody(data.getBody());
        return data;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        commentRepository.deleteByPostId(id);
        postRepository.deleteById(id);
    }
}
// END
