package dasturlash.uz.controller.history;

import dasturlash.uz.dto.history.EmailDTO;
import dasturlash.uz.dto.history.SmsDTO;
import dasturlash.uz.entity.history.EmailHistoryEntity;
import dasturlash.uz.service.AuthService;
import dasturlash.uz.service.history.EmailHistoryService;
import dasturlash.uz.service.history.SmsHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("sms")
public class SmsHistory {

    @Autowired
    private AuthService authService;
    @Autowired
    private SmsHistoryService smsHistoryService;

    @GetMapping("/history/{phone}")
    public ResponseEntity<String> registrationResend(@PathVariable("phone") String phone) {
        String body = authService.registrationResendPhone(phone);
        return ResponseEntity.ok().body(body);
    }

   /* @GetMapping("/by/{phone}")
    public ResponseEntity<EmailHistoryEntity> getByPhone(@PathVariable("phone") String email, @Valid @RequestBody SmsDTO smsDTO) {
        EmailHistoryEntity body = smsHistoryService.getByPhone(email, smsDTO);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/by/{created_date}")
    public ResponseEntity<EmailHistoryEntity> getByCreatedDate(@PathVariable("created_date") LocalDateTime createdDate, @Valid @RequestBody EmailDTO emailDTO) {
        EmailHistoryEntity body = smsHistoryService.getByCreatedDate(createdDate, emailDTO);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<EmailDTO>> emailPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<EmailDTO> body = smsHistoryService.paginationPhone(page - 1, size);
        return ResponseEntity.ok().body(body);
    }*/
}
