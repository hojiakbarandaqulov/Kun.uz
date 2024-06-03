package dasturlash.uz.dto.auth;

import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "surname required")
    private String surname;
    /*@NotBlank(message = "phone required")
    private String phone;*/
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "password required")
    private String password;
    private ProfileRole role;
}
