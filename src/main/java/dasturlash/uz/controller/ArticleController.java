package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleDTO;
import dasturlash.uz.dto.create.ArticleCreateDTO;
import dasturlash.uz.dto.response.ArticleRequestDTO;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleRequestDTO> createArticle(@RequestBody ArticleRequestDTO articleDTO) {
        ArticleRequestDTO response = articleService.create(articleDTO);
        return ResponseEntity.ok().body(response);
    }
   /* @PostMapping("")
    public ResponseEntity<ArticleRequestDTO> create(@RequestBody @Valid ArticleRequestDTO dto) {
        return ResponseEntity.ok(articleService.createArticle(dto));
    }*/
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
