package dasturlash.uz.controller.history;

import dasturlash.uz.dto.history.EmailDTO;
import dasturlash.uz.entity.history.EmailHistoryEntity;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.history.EmailHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/email")
public class EmailHistoryController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailHistoryService emailHistoryService;

    @GetMapping("/adm/{email}")
    public ResponseEntity<String> registrationResend(@PathVariable("email") String email) {
        String body = authService.registrationResendEmail(email);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/adm/byEmail/{email}")
    public ResponseEntity<EmailDTO> ByEmail(@PathVariable("email") String email, EmailDTO emailDTO) {
        EmailDTO body = emailHistoryService.getByEmail(email, emailDTO);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/adm/date/{created_date}")
    public ResponseEntity<EmailDTO> getByCreatedDate(@Valid @RequestBody EmailDTO emailDTO, @PathVariable LocalDateTime created_date) {
        EmailDTO body = emailHistoryService.getByCreatedDate(emailDTO, created_date);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl<EmailDTO>> emailPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<EmailDTO> body = emailHistoryService.paginationEmail(page - 1, size);
        return ResponseEntity.ok().body(body);
    }
}
