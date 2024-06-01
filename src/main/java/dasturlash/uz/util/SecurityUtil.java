package dasturlash.uz.util;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.exp.AppForbiddenException;

public class SecurityUtil {

    public static JwtDTO getJwtDTO(String token) {
        String jwt = token.substring(7); // Bearer eyJhb
        JwtDTO dto = JwtUtil.decode(jwt);
        return dto;
    }

    public static JwtDTO getJwtDTO(String token, ProfileRole requiredRole) {
        JwtDTO dto = getJwtDTO(token);
        if(!dto.getRole().equals(requiredRole)){
            throw new AppForbiddenException("Method not allowed");
        }
        return dto;
    }
}