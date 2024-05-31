package dasturlash.uz.controller;

import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.auth.AuthResponseDTO;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.dto.auth.LoginDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.util.HttpRequestUtil;
import dasturlash.uz.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

     @PostMapping("/registration")
     public ResponseEntity<String> registration(@Valid @RequestBody RegistrationDTO dto) {
         String body = authService.registration(dto);
         return ResponseEntity.ok().body(body);
     }
  /*  @PostMapping("/registration")
    public ResponseEntity<String> registrationEmail(@Valid @RequestBody RegistrationDTO dto,
                                                    @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_USER);
        String body = authService.registration(dto);
        return ResponseEntity.ok().body(body);
    }*/
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody LoginDTO dto) {
        ProfileDTO body = authService.loginPhone(dto);
        return ResponseEntity.ok().body(body);
    }
    /* @PostMapping("/login/phone")
     public ResponseEntity<ProfileDTO> loginPhone(@Valid @RequestBody LoginDTO dto) {
         ProfileDTO body = authService.loginPhone(dto);
         return ResponseEntity.ok().body(body);
     }*/
    @GetMapping("/verification/{userId}")
    public ResponseEntity<String> verification(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerification(userId);
        return ResponseEntity.ok().body(body);
    }
    /*@GetMapping("/registration/resend/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email,
              @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO=SecurityUtil.getJwtDTO(token);
        String body = authService.registrationResendEmail(email);
        return ResponseEntity.ok().body(body);
    }*/
    @GetMapping("/sms/{phone}")
    public ResponseEntity<String> registrationResendPhone(@PathVariable("phone") String phone) {
        String body = authService.registrationResendPhone(phone);
        return ResponseEntity.ok().body(body);
    }
}
