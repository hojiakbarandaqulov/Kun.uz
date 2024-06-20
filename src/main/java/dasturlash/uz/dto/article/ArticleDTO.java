package dasturlash.uz.dto.article;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleDTO {
    @Id
    @GeneratedValue
    private String id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String content;
    private int sharedCount;
    private String imageId;
    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publisherId;
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private boolean visible;
    private int viewCount;
    private String image;
    private List<Integer> type;

}
