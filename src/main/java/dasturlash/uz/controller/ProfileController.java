package dasturlash.uz.controller;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO profileDTO){
        ProfileDTO response=profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable ("id") Integer id, @RequestBody ProfileDTO dto){
        profileService.update(id, dto);
        return ResponseEntity.ok().body(true);
    }
}
