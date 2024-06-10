package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "comment")
    private String comment;
//    private String articleId;
    @ManyToOne()
    @JoinColumn(name = "article_id", insertable = false, updatable = false)
    private ArticleEntity article;
    private LocalDateTime createdDate=LocalDateTime.now();
    private LocalDateTime updatedDate;
//    private String profileId;
    @ManyToOne()
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
    private String replyId;
    private Boolean visible=Boolean.TRUE;
}
