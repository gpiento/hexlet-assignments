package exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
