package dasturlash.uz.dto.article;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ArticleDTO {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String content;
    private int sharedCount;
    private Integer imageId;
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
    private List<Integer> type;

}
