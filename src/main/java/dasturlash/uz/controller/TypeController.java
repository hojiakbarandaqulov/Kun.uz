package dasturlash.uz.controller;
import dasturlash.uz.dto.TypeDTO;
import dasturlash.uz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

   /* @PostMapping
    public ResponseEntity<TypeDTO> createType(@RequestBody TypeDTO type) {
        return ResponseEntity.ok(typeService.createType(type));
    }*/
}