package dasturlash.uz.dto.auth;

import dasturlash.uz.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtDTO {
    private Integer id;
    private ProfileRole role;

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JwtDTO(Integer id) {
        this.id = id;
    }
}
