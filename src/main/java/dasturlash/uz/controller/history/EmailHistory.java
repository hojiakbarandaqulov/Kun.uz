package dasturlash.uz.controller.history;

import dasturlash.uz.dto.history.EmailDTO;
import dasturlash.uz.entity.history.EmailHistoryEntity;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.history.EmailHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/email")
public class EmailHistory {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailHistoryService emailHistoryService;

    @GetMapping("/history/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email) {
        String body = authService.registrationResendEmail(email);
        return ResponseEntity.ok().body(body);
    }
    @GetMapping("/by/{email}")
    public ResponseEntity<EmailHistoryEntity> ByEmail(@PathVariable("email") String email) {
        EmailHistoryEntity body = emailHistoryService.getByEmail(email);
        return ResponseEntity.ok().body(body);
    }
   /* @GetMapping("/created_date/{created_date}")
    public ResponseEntity<EmailHistoryEntity> getByCreatedDate(@PathVariable("created_date") LocalDateTime createdDate, @Valid @RequestBody EmailDTO emailDTO) {
        EmailHistoryEntity body = emailHistoryService.getByCreatedDate(createdDate, emailDTO);
        return ResponseEntity.ok().body(body);
    }*/

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<EmailDTO>> emailPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
         PageImpl<EmailDTO> body = emailHistoryService.paginationEmail(page - 1, size);
        return ResponseEntity.ok().body(body);
    }
}
