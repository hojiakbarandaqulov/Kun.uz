package dasturlash.uz.dto.create;

//import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.ProfileRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;

@Setter
@Getter

public class ArticleCreateDTO {

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

    @NotNull(message = "Category ID cannot be null")
    private Array articleType;

    private ProfileRole role;
}
