package dasturlash.uz.controller;

import dasturlash.uz.dto.create.RegionCreateDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/region")
@RestController
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping(value = "/adm/create", method = RequestMethod.POST)
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO region) {
        RegionDTO response = regionService.create(region);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id, @Valid @RequestBody RegionCreateDTO dto) {
        regionService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id/*@RequestHeader("Authorization") String token*/) {
        regionService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/adm/regionAll")
    public ResponseEntity<List<RegionDTO>> all(/*@RequestHeader("Authorization") String token*/) {
        return ResponseEntity.ok().body(regionService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        List<RegionDTO> regionDTOList = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(regionDTOList);
    }
}
