package dasturlash.uz.controller.history;

import dasturlash.uz.controller.AuthController;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.history.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailHistory {
   @Autowired
   private AuthService authService;
    @GetMapping("/history/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email) {
        String body = authService.registrationResendEmail(email);
        return ResponseEntity.ok().body(body);
    }
//    @GetMapping()
}
