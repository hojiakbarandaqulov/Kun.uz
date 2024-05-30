package dasturlash.uz.controller;

import dasturlash.uz.dto.RegionCreateDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.enums.Language;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.RegionService;
import dasturlash.uz.util.SecurityUtil;
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

   @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO region,
                                            @RequestHeader("Authorization") String token){
       SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_USER);
        RegionDTO response=regionService.create(region);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@Valid @RequestBody RegionCreateDTO dto,
                                                @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = SecurityUtil.getJwtDTO(token);
        regionService.update(jwtDTO.getId(), dto);
        return ResponseEntity.ok().body(true);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(/*@PathVariable ("id") Integer id,*/
                                          @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = SecurityUtil.getJwtDTO(token);
        regionService.delete(jwtDTO.getId());
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/regionAll")
    public ResponseEntity<List<RegionDTO>> all(@RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = SecurityUtil.getJwtDTO(token);
        return ResponseEntity.ok().body(regionService.getAll(jwtDTO.getId()));
    }
    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAllByLang(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang
                                                       /* @RequestHeader("Authorization") String token*/) {
//        JwtDTO jwtDTO = SecurityUtil.getJwtDTO(token);
        List<RegionDTO> regionDTOList = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(regionDTOList);
    }
}
