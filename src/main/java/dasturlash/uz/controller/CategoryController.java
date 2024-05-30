package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryCreateDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.CategoryService;
import dasturlash.uz.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryCreateDTO region,
                                              @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_USER);
        CategoryDTO response = categoryService.create(region);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@Valid @RequestBody CategoryCreateDTO dto,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO=SecurityUtil.getJwtDTO(token);
        Boolean result = categoryService.update(jwtDTO.getId(), dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable("id") Integer id,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO=SecurityUtil.getJwtDTO(token);
        Boolean result = categoryService.delete(jwtDTO.getId());
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List<CategoryDTO> categoryList = categoryService.getAllByLanguage(lang);
        return ResponseEntity.ok().body(categoryList);
    }
}
