package dasturlash.uz.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleRequestDTO {

    private String id;
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Content cannot be null")
    private String content;

    @Column(name = "shared_count")
    private Integer sharedCount = 0;
    @NotNull(message = "Image ID cannot be null")
    private String imageId;

    @NotNull(message = "Region ID cannot be null")
    private Integer regionId;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Role cannot be null")
    private ProfileRole role;

    @NotNull(message = "Article cannot be null")
    private ArticleStatus status;

    private RegionDTO region;                   // BU yangilik qayer(region) da sodir bo'ldi

    private Long likeCount;
    private Long commentCount;

    private CategoryDTO category;
    private LocalDateTime publishDate;
    private AttachDTO image;
    private List<Integer> articleType;

}
