package dasturlash.uz.controller;

import dasturlash.uz.dto.*;
import dasturlash.uz.dto.create.ProfileCreateDTO;
import dasturlash.uz.dto.filter.ProfileFilterDTO;
import dasturlash.uz.dto.update.ProfileUpdateDTO;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/profile")
@RestController
public class ProfileController {

    private  ProfileService profileService;

//    @Autowired
//    public ProfileController(ProfileService profileService) {
//        this.profileService = profileService;
//    }

    @PostMapping("/adm/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileDTO) {
        ProfileDTO response = profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/current/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Integer id, @RequestBody ProfileUpdateDTO profile) {
        profileService.updateUser(id, profile);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @Valid @RequestBody ProfileCreateDTO profile) {
        profileService.update(id, profile);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/adm/profilePagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> typeList = profileService.getAllPagination(page - 1, size);
        return ResponseEntity.ok().body(typeList);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String token) {
        profileService.deleteId(id);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProfileFilterDTO filter) {
        PageImpl<ProfileDTO> studentDTOList = profileService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }
}
