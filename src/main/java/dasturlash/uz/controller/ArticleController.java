package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.article.ArticleCreateDTO;
import dasturlash.uz.dto.article.ArticleRequestDTO;

//import dasturlash.uz.service.ArticleService;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.TypesEntity;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/moderator/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@Valid @RequestBody ArticleCreateDTO createDto,
                                                    @PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.update(id, createDto));
    }

    @PreAuthorize("hasAnyRole('MODERATOR','PUBLISHER')")
    @PutMapping("/v1/moderator/{id}")
    public ResponseEntity<Boolean> deleteArticle(@PathVariable("id") String id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok().body(true);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping("/changeByStatus/{id}")
    public ResponseEntity<Boolean> changeByStatus(@PathVariable("id") String id) {
        articleService.changeByStatus(id);
        return ResponseEntity.ok().body(true);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/types/{id}")
    public ResponseEntity<List<ArticleRequestDTO>> getLast5ArticlesByType(@PathVariable("id") Integer id) {
        List<ArticleRequestDTO> last5ByTypes = articleService.getLast5ByTypes(id);
        return ResponseEntity.ok().body(last5ByTypes);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/types/3/{id}")
    public ResponseEntity<List<ArticleRequestDTO>> getLast3ArticlesByType(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.getLast3ByTypes(id));
    }

//    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping("/last/eight")
    public ResponseEntity<List<ArticleRequestDTO>> getLast8ArticlesByType(@RequestBody List<String> id) {
        return ResponseEntity.ok(articleService.getLast8ByTypes(id));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/{id}")
    public ResponseEntity<List<ArticleRequestDTO>> getArticlesByType(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleService.getArticleByTypes(id));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/read/4")
    public ResponseEntity<List<ArticleEntity>> getRead4Articles() {
        List<ArticleEntity> read4Articles = articleService.getRead4Articles();
        return ResponseEntity.ok(read4Articles);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/tagName/{name}")
    public ResponseEntity<List<ArticleEntity>> getRead4Articles(@PathVariable("name") String name) {
        List<ArticleEntity> read4Articles = articleService.findLast4ByTagName(name);
        return ResponseEntity.ok(read4Articles);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/list")
    public ResponseEntity<List<ArticleEntity>> getArticleByTypeAndRegionId(@RequestParam("types") TypesEntity types,
                                                                           @RequestParam("regionId") Integer regionId) {
        return ResponseEntity.ok(articleService.findLast5ByTypesAndRegionKey(types, regionId));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/regionId/{id}")
    public ResponseEntity<PageImpl<RegionDTO>> getRegionId(@PathVariable("id") Integer regionId,
                                                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                           @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageImpl<RegionDTO> read4Articles = articleService.findRegionId(regionId, page-1,size);
        return ResponseEntity.ok(read4Articles);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/article/category/{id}")
    public ResponseEntity<Optional<CategoryEntity>> getCategoryList(@PathVariable("id") Integer id){
        Optional<CategoryEntity> categoryId = articleService.getCategoryId(id);
        return ResponseEntity.ok().body(categoryId);
    }


 /*   @GetMapping("/by-category")
    public Page<ArticleEntity> getArticlesByCategoryKey(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return articleService.findByCategoryId(page-1, size, categoryId);
    }*/
}

