package dasturlash.uz.controller;

import dasturlash.uz.dto.TypesDTO;
import dasturlash.uz.service.RegionService;
import dasturlash.uz.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/types")
@RestController
public class TypesController {
    @Autowired
    private TypesService typesService;

    @PostMapping("/created")
    public ResponseEntity<TypesDTO> create(@RequestBody TypesDTO typesDTO){
        TypesDTO response=typesService.create(typesDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable ("id") Integer id, @RequestBody TypesDTO dto){
        typesService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id){
        typesService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/TypeAllPagination")
    public  ResponseEntity<PageImpl<TypesDTO>>getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size){
        PageImpl<TypesDTO> typeList=typesService.getAllPagination(page-1,size);
        return ResponseEntity.ok().body(typeList);
    }

    @GetMapping("/GetByLanguage/{language}")
    public List<TypesDTO> getByLanguage(/*@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,*/ @PathVariable String language){
        return typesService.getByLanguage(language);
    }
}
