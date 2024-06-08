package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleCreateDTO;
import dasturlash.uz.dto.article.ArticleRequestDTO;

//import dasturlash.uz.service.ArticleService;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ArticleRequestDTO> createArticle(@RequestBody ArticleCreateDTO create) {
        ArticleRequestDTO article = articleService.createArticle(create);
        return ResponseEntity.ok().body(article);
    }

//    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/moderator/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@Valid @RequestBody ArticleCreateDTO createDto,
                                                    @PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.update(id, createDto));
    }

//    @PreAuthorize("hasAnyRole('MODERATOR','PUBLISHER')")
    @PutMapping("/v1/moderator/{id}")
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
/*

     @GetMapping("/typeIntermediate/{type}")
     public ResponseEntity<List<ArticleRequestDTO>> getLast5ArticlesByType(@PathVariable Integer type) {
         List<ArticleRequestDTO> articles = articleService.TypeById(type);
         return new ResponseEntity<>(articles, HttpStatus.OK);
     }
*/


   /* @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id") UUID id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.updateArticle(id, articleRequestDTO);
        return ResponseEntity.ok().body(true);
    }*/


    /* @GetMapping("/typeIntermediate/{type}")
     public ResponseEntity<List<ArticleRequestDTO>> getLast5ArticlesByType( @PathVariable String type) {
         List<ArticleRequestDTO> articles = articleService.TypeById(type);
         return new ResponseEntity<>(articles, HttpStatus.OK);
     }*/


  /*  @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateArticle(@PathVariable("id") UUID id, @RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.updateArticle(id, articleRequestDTO);
        return ResponseEntity.ok().body(true);
    }*/

