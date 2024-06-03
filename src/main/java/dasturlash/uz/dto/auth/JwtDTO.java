package dasturlash.uz.dto.auth;

import dasturlash.uz.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtDTO {
    private Integer id;
    private String username;
    private ProfileRole role;

    public JwtDTO(Integer id, String userName, ProfileRole role) {
        this.id = id;
        this.username = userName;
        this.role = role;
    }

    public JwtDTO(Integer id) {
        this.id = id;
    }
}
