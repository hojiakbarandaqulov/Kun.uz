package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionCreateDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/region")
@RestController
public class RegionController {
    @Autowired
    private RegionService regionService;
    @PostMapping("/created")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO region){
        RegionDTO response=regionService.create(region);
        return ResponseEntity.ok().body(response);
    }
/*    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid  @RequestBody RegionCreateDTO dto) {
        Boolean result = regionService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid @RequestBody RegionCreateDTO dto) {
        Boolean result = regionService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id){
        regionService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/regionAll")
    public ResponseEntity<List<RegionDTO>> all() {
        return ResponseEntity.ok().body(regionService.getAll());
    }


    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(
            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List<RegionDTO> regionDTOList = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(regionDTOList);
    }

}
