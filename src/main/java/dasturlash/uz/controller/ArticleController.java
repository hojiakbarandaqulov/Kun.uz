package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.dto.article.ArticleStatusDTO;

import dasturlash.uz.dto.article.ArticleStatusDTO;

import dasturlash.uz.dto.article.ArticleStatusDTO;

import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

import java.util.UUID;

//@PreAuthorize("hasRole('MODERATOR')")
@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/moderator")
    public ResponseEntity<ArticleRequestDTO> createArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        ArticleRequestDTO article = articleService.createArticle(articleRequestDTO);
        return ResponseEntity.ok().body(article);
    }


    /*@PutMapping("/moderator/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id") String id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.update(id, articleRequestDTO);
        return ResponseEntity.ok().body(true);
    }*/
    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/moderator/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@Valid @RequestBody ArticleRequestDTO createDto,
                                                    @PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.update(id, createDto));
    }

    @PreAuthorize("hasAnyRole('MODERATOR','PUBLISHER')")
    @PutMapping("/moderator/{id}")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("id") String id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/changeByStatus/{id}")
    public ResponseEntity<Boolean> changeByStatus(@PathVariable("id") String id) {
        articleService.changeByStatus(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/typeIntermediate/{type}")
    public ResponseEntity<List<ArticleRequestDTO>> getLast5ArticlesByType(@PathVariable Integer type) {
        List<ArticleRequestDTO> articles = articleService.TypeById(type);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

}

