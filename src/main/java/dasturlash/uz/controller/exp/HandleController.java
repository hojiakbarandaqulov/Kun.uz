package dasturlash.uz.controller.exp;


import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.exp.AppForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleController {
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler({AppBadException.class})
    public ResponseEntity<?> handle(AppBadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AppForbiddenException.class)
    public ResponseEntity<String> handler(AppForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
