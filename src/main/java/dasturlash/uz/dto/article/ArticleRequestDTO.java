package dasturlash.uz.dto.article;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;

=======
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
=======
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
=======
>>>>>>> 513d4ac3166946f1a193142457d5ea66b31a38a9
>>>>>>> 4d235e882bdc6797ede9322bd0f35f5138c42c49
>>>>>>> 2e61bf5e2689c94a813a385e9a9afc0767de9c34
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
<<<<<<< HEAD
@JsonInclude(JsonInclude.Include.NON_NULL)

=======
<<<<<<< HEAD
@JsonInclude(JsonInclude.Include.NON_NULL)
=======
<<<<<<< HEAD
@JsonInclude(JsonInclude.Include.NON_NULL)
=======
>>>>>>> 513d4ac3166946f1a193142457d5ea66b31a38a9
>>>>>>> 4d235e882bdc6797ede9322bd0f35f5138c42c49
>>>>>>> 2e61bf5e2689c94a813a385e9a9afc0767de9c34
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

<<<<<<< HEAD
    @NotNull(message = "Role cannot be null")
    private ProfileRole role;
=======
<<<<<<< HEAD
    @NotNull(message = "Role cannot be null")
    private ProfileRole role;
/*    @ElementCollection
    private List<String> articleType;*/
=======
<<<<<<< HEAD
    @NotNull(message = "Role cannot be null")
    private ProfileRole role;
=======
    @ElementCollection
    private List<String> articleType;
>>>>>>> 513d4ac3166946f1a193142457d5ea66b31a38a9
>>>>>>> 4d235e882bdc6797ede9322bd0f35f5138c42c49
>>>>>>> 2e61bf5e2689c94a813a385e9a9afc0767de9c34

    @NotNull(message = "Article cannot be null")
    private ArticleStatus status;
//    @ElementCollection

    private List<Integer> articleType;

}
