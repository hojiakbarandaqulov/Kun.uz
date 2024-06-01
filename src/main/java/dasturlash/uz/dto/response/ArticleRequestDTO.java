package dasturlash.uz.dto.response;

import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Array;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter

public class ArticleRequestDTO {


    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Content cannot be null")
    private String content;

    @NotNull(message = "Image ID cannot be null")
    private Integer imageId;

    @NotNull(message = "Region ID cannot be null")
    private Integer regionId;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Article type cannot It didn't fit")
    private Array articleType;


}
