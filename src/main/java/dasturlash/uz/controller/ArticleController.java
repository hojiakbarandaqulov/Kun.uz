package dasturlash.uz.controller;

import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.dto.create.ArticleCreateDTO;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.ArticleService;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleCreateDTO articleDTO) {
        ArticleDTO response = articleService.createArticle(articleDTO);
        return ResponseEntity.ok().body(response);
    }
/*
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable UUID id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable UUID id, @RequestBody ArticleDTO articleDetails) {
        return ResponseEntity.ok(articleService.updateArticle(id, articleDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable UUID id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }*/
}
