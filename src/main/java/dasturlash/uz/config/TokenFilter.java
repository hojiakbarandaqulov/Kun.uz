/*
package dasturlash.uz.config;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class TokenFilter extends GenericFilter{

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message", "Token Not Found Mazgi.");
            return;
        }

        String token = authHeader.substring(7);
        JwtDTO jwtDto;
        try {
            jwtDto = JwtUtil.decode(token);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Message", "Token Not Valid");
            return;
        }

        request.setAttribute("id", jwtDto.getId());
        request.setAttribute("role", jwtDto.getRole());
        filterChain.doFilter(request, response);

    }
}
*/
