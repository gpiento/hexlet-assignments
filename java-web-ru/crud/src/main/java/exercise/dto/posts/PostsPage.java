package exercise.dto.posts;

import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


// BEGIN
@AllArgsConstructor
@Getter
public class PostsPage {

    private List<Post> posts;
    private Long page;

    public Long getPage() {

        return page > 0 ? page : 1L;
    }
}
// END


