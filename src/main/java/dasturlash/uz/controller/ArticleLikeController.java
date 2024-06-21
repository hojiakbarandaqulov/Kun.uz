package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.service.ArticleLikeService;
import dasturlash.uz.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("/api/v1")
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    public ArticleLikeController(ArticleLikeService articleLikeService) {
        this.articleLikeService = articleLikeService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String articleId,
                                        HttpServletRequest request) {
        Integer id = (Integer)  request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.like(articleId, id));
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String articleId,
                                           @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.dislike(articleId, jwt.getId()));
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String articleId,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.delete(articleId, jwt.getId()));
    }
}
