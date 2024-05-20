package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.service.CategoryService;
import dasturlash.uz.service.RegionService;
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
        return ResponseEntity.ok().body(response);
    }
  /*  @PostMapping("/created")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO){
        RegionDTO response=regionService.create(regionDTO);
        return ResponseEntity.ok().body(response);
    }*/
    @PutMapping("/update/{id}")
        categoryService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.ok().body(true);
    }
    }
    }
}
