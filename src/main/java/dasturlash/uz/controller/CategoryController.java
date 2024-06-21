package dasturlash.uz.controller;

import dasturlash.uz.dto.create.CategoryCreateDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/adm/create")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryCreateDTO region) {
        CategoryDTO response = categoryService.create(region);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id, @Valid @RequestBody CategoryCreateDTO dto) {
        Boolean result = categoryService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id) {
        Boolean result = categoryService.delete(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/adm/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List<CategoryDTO> categoryList = categoryService.getAllByLanguage(lang);
        return ResponseEntity.ok().body(categoryList);
    }
}
