package dasturlash.uz.dto.create;

//import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
@Setter
@Getter

public class ArticleCreateDTO {
    private String description;
    private String content;
    private Integer imageId;
    private Integer regionId;
//    private CategoryDTO category;
    private Integer moderatorId;
    private Array articleType;
    private ProfileRole role;
}
