package dasturlash.uz.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCreateDTO {
    @NotNull(message = "title cannot be null ")
    private String title;
    @NotNull(message = "description cannot be null ")
    private String description;
    @NotNull(message = "content cannot be null ")
    private String content;
    @NotNull(message = "imageId cannot be null ")
    private String imageId;
    @NotNull(message = "regionId cannot be null ")
    private Integer regionId;
    @NotNull(message = "categoryId cannot be null ")
    private Integer categoryId;
    private List<Integer> typesList;
}
