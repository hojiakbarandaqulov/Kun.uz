package dasturlash.uz.dto;

import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private String id;
    private String comment;
    //    private String articleId;
    private ArticleEntity article;
    private LocalDateTime createdDate=LocalDateTime.now();
    private LocalDateTime updatedDate;
    //    private ProfileEntity profileId;
    private ProfileDTO profile;
    private String replyId;
    private Boolean visible;
}
