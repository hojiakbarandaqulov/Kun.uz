package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/verification/{userId}")
    public ResponseEntity<String> verification(@PathVariable("userId") Integer userId) {
        String body = authService.authorizationVerification(userId);
        return ResponseEntity.ok().body(body);
    }

}
