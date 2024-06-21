package dasturlash.uz.controller;

import dasturlash.uz.dto.create.TypesCreatedDto;
import dasturlash.uz.dto.TypesDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.service.TypesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/types")
@RestController
public class TypesController {

    private final TypesService typesService;

    public TypesController(TypesService typesService) {
        this.typesService = typesService;
    }

    @PostMapping("/adm/create")
    public ResponseEntity<TypesDTO> create(@Valid @RequestBody TypesCreatedDto dto) {
        TypesDTO response = typesService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id, @Valid @RequestBody TypesDTO dto) {
        typesService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        typesService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/adm/typeAllPagination")
    public ResponseEntity<PageImpl<TypesDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<TypesDTO> typeList = typesService.getAllPagination(page - 1, size);
        return ResponseEntity.ok().body(typeList);
    }

    @GetMapping("/language")
    public List<TypesDTO> getByLanguage(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
        return typesService.getAllByLang(language);
    }

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
}
