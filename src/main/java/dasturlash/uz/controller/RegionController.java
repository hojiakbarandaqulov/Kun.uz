package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("region")
@RestController
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/created")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO){
        RegionDTO response=regionService.create(regionDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable ("id") Integer id, @RequestBody RegionDTO dto){
        regionService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id){
        regionService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/RegionAll")
    public List<RegionDTO> getAll(){
        return regionService.getAll();
    }

    @GetMapping("/GetByLanguage/{language}")
    public List<RegionDTO> getByLanguage(/*@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language,*/ @PathVariable String language){
        return regionService.getByLanguage(language);
    }
}
