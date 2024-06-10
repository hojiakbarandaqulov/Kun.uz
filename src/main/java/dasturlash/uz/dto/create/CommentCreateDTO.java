package dasturlash.uz.dto.create;

import dasturlash.uz.entity.ArticleEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentCreateDTO {
    private String comment;
    private String articleId;
    private String replyId;
}
