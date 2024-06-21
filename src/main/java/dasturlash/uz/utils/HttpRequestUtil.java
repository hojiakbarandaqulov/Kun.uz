package dasturlash.uz.utils;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.enums.ProfileRole;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

    public static JwtDTO getJwtDTO(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        String username = (String) request.getAttribute("username");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id,username, role);
        return dto;
    }

  /*  public static JwtDTO getJwtDTO(HttpServletRequest request, ProfileRole requiredRole) {
        JwtDTO dto = getJwtDTO(request);
        if (!dto.getRole().equals(requiredRole)) {
            throw new AppForbiddenException("Method not allowed");
        }
        return dto;
    }*/
}
