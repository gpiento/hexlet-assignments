package exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// BEGIN
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private long id;
    private String body;
}
// END
