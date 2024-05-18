package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryCreateDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.service.CategoryService;
import dasturlash.uz.service.RegionService;
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
    @PostMapping("/created")
    public ResponseEntity<CategoryDTO> create(@Valid  @RequestBody CategoryCreateDTO category){
        CategoryDTO response=categoryService.create(category);
        return ResponseEntity.ok().body(response);
    }
  /*  @PostMapping("/created")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO){
        RegionDTO response=regionService.create(regionDTO);
        return ResponseEntity.ok().body(response);
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable ("id") Integer id, @Valid @RequestBody CategoryDTO dto){
        categoryService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> all() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }
    @GetMapping("/language/")
    public ResponseEntity<List<CategoryDTO>>getByLanguage(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language){
        List<CategoryDTO> allByLanguage = categoryService.getAllByLanguage(language);
        return ResponseEntity.ok().body(allByLanguage);
    }
//    @GetMapping("/GetByLanguage/{language}")
//    public List<CategoryDTO> getByLanguage2(/*@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,*/ @PathVariable Language language){
//        return categoryService.getByLanguage(language);
//    }
}
