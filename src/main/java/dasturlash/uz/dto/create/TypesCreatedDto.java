package dasturlash.uz.dto.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypesCreatedDto {

    @NotNull
    private Integer orderNumber;
    @NotBlank(message = "nameUz required")
    private String nameUz;
    @NotBlank(message = "nameRu required")
    private String nameRu;
    @NotBlank(message = "nameEn required")
    private String nameEn;
}
