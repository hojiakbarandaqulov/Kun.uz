package dasturlash.uz.controller;

import dasturlash.uz.dto.SavedArticleDTO;
import dasturlash.uz.entity.SavedArticleEntity;
import dasturlash.uz.service.ArticleService;
import dasturlash.uz.service.SavedArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/saved/article")
public class SavedArticleController {

    private SavedArticleService savedArticleService;

    private ArticleService articleService;

    public SavedArticleController(SavedArticleService savedArticleService, ArticleService articleService) {
        this.savedArticleService = savedArticleService;
        this.articleService = articleService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<SavedArticleDTO> create(@PathVariable("id") String id) {
        SavedArticleEntity savedArticle = savedArticleService.create(id);
        SavedArticleDTO response = savedArticleService.convertToDTO(savedArticle);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id) {
        savedArticleService.delete(id);
        return true;
    }

    @GetMapping("/saved/{id}")
    public ResponseEntity<List<SavedArticleDTO>> getProfileSavedArticles(@PathVariable("id") Integer profileId) {
        List<SavedArticleDTO> savedArticles = savedArticleService.getProfileSavedArticles(profileId);
        return ResponseEntity.ok(savedArticles);
    }
}
