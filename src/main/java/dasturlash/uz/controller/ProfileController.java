package dasturlash.uz.controller;

import dasturlash.uz.dto.*;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileDTO){
        ProfileDTO response=profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRegion(@PathVariable("id") Integer id,
                                                @Valid @RequestBody ProfileCreateDTO dto) {
        Boolean result = profileService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
/* @PutMapping("/update/{role}")
    public ResponseEntity<Boolean> updateUserRegion(@PathVariable("role") ProfileRole role,
                                                    @Valid @RequestBody ProfileCreateDTO dto) {
        Boolean result = profileService.updateRole(role, dto);
        return ResponseEntity.ok().body(result);
    }*/
    @GetMapping("/profilePagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> typeList = profileService.getAllPagination(page - 1, size);
        return ResponseEntity.ok().body(typeList);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        profileService.delete(id);
        return ResponseEntity.ok().body(true);
    }
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProfileFilterDTO filter) {
        PageImpl<ProfileDTO> studentDTOList = profileService.filter(filter,page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }


}
