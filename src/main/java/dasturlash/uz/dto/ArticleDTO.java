package dasturlash.uz.dto;

import dasturlash.uz.enums.ArticleStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class ArticleDTO {
    private UUID id;
    private String title;
    private String description;
    private String content;
    private int sharedCount;
    private Integer imageId;
    private Integer regionId;
//    private CategoryDTO category;
    private Integer moderatorId;
    private Integer publisherId;
    private ArticleStatus articleStatus;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private boolean visible;
    private int viewCount;
    private Set<TypeDTO> types;
}
