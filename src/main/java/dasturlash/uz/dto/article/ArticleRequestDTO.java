package dasturlash.uz.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ArticleRequestDTO {

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
    private Integer imageId;

    @NotNull(message = "Region ID cannot be null")
    private Integer regionId;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Role cannot be null")
    private ProfileRole role;
/*    @ElementCollection
    private List<String> articleType;*/

 /*   @ElementCollection
    private List<String> articleType;
*/

    @NotNull(message = "Article cannot be null")
    private ArticleStatus status;
//    @ElementCollection

    private List<Integer> articleType;

}
