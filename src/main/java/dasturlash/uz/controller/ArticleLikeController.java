package dasturlash.uz.controller;

import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.service.ArticleLikeService;
import dasturlash.uz.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ArticleLikeController {

    private final ArticleLikeService articleLikeService;

    public ArticleLikeController(ArticleLikeService articleLikeService) {
        this.articleLikeService = articleLikeService;
    }
  /*  @PostMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleLikeService.like(id));
    }

    @GetMapping("dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleLikeService.dislike(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String articleId) {
        return ResponseEntity.ok(articleLikeService.delete(articleId));
    }*/


    ////
    @PostMapping("/private/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String articleId,
                                        HttpServletRequest request) {
        Integer id = (Integer)  request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.like(articleId, id));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String articleId,
                                           @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.dislike(articleId, jwt.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String articleId,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.delete(articleId, jwt.getId()));
    }
}
