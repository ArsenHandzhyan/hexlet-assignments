package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

// BEGIN

@AllArgsConstructor
@Getter
public class PostsPage extends BasePage {
    private List<Post> posts;

    @Override
    public String getFlash() {
        return super.getFlash();
    }

    @Override
    public void setFlash(String flash) {
        super.setFlash(flash);
    }
}
// END
