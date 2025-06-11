package epicode.u5d7hw.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogPostRequestPayload {
    private String category;
    private String title;
    private String content;
    private double readingTime;
    private int authorId;
}
