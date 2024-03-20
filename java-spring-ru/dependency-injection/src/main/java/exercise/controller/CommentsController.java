package exercise.controller;

import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Comment> index(@RequestParam(defaultValue = "10") Integer limit) {
        return commentRepository.findAll().stream().limit(limit).toList();
    }

    @PostMapping("")
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        Comment createdComment = commentRepository.save(comment);
        return ResponseEntity.created(URI.create("/comments/" + createdComment.getId())).body(createdComment);
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable String id) {
        return commentRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException(id + " Not Found"));
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable String id, @RequestBody Comment data) {
        var maybePage = commentRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResourceNotFoundException("Comments with id " + id + " not found"));
        maybePage.setBody(data.getBody());
        return data;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
// END
