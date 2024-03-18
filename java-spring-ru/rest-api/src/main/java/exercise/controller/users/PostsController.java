package exercise.controller.users;

import exercise.Data;
import exercise.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// BEGIN
@RestController
@RequestMapping("/api/users/{id}/posts")
public class PostsController {

    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    @GetMapping
    public List<Post> getAllPostsByUserId(@PathVariable Integer id) {
        return posts.stream()
                .filter(post -> post.getUserId() == id)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPostForUser(@PathVariable Integer id, @RequestBody Post newPost) {
        newPost.setUserId(id);
        posts.add(newPost);
        return newPost;
    }
}
// END
