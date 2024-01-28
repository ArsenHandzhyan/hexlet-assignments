package exercise.dto.posts;

import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostsPage {
    private List<Post> posts;
    private int currentPage;
    private int totalPages;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
}
