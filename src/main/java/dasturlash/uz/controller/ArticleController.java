package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleRequestDTO> createArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        ArticleRequestDTO article = articleService.createArticle(articleRequestDTO);
        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id") UUID id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.updateArticle(id,articleRequestDTO);
        return ResponseEntity.ok().body(true);
    }
}
// User and Admin profile da umuman yoq bolsa qayerdan userni