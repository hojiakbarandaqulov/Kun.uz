package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
