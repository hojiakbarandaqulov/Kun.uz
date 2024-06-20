package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.service.CommentLikeService;
import dasturlash.uz.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment/like")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") Integer commentId,
                                        HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(commentLikeService.like(commentId, id));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") Integer commentId,
                                           @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.dislike(commentId, jwt.getId()));
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer commentId,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(commentLikeService.delete(commentId, jwt.getId()));
    }
}
