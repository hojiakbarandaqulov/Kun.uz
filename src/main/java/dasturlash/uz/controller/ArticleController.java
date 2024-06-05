package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.dto.article.ArticleStatusDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/moderator")
    public ResponseEntity<ArticleRequestDTO> createArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        ArticleRequestDTO article = articleService.createArticle(articleRequestDTO);
        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/moderator/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id") String id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.updateArticle(id, articleRequestDTO);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/moderator/{id}")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("id") UUID id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/changeByStatus/{id}")
    public ResponseEntity<Boolean> changeByStatus(@PathVariable("id") UUID id, @RequestBody ArticleStatusDTO statusDTO) {
        articleService.changeByStatus(id, statusDTO);
        return ResponseEntity.ok().body(true);
    }

    /* @GetMapping("/typeIntermediate/{type}")
     public ResponseEntity<List<ArticleRequestDTO>> getLast5ArticlesByType( @PathVariable String type) {
         List<ArticleRequestDTO> articles = articleService.TypeById(type);
         return new ResponseEntity<>(articles, HttpStatus.OK);
     }*/

}