package dasturlash.uz.controller;

import dasturlash.uz.dto.*;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.dto.create.ProfileCreateDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.ProfileService;
import dasturlash.uz.util.HttpRequestUtil;
import dasturlash.uz.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileDTO){
        ProfileDTO response = profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/adm/current")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id")Integer id,@RequestBody ProfileUpdateDTO profile) {
        profileService.updateUser(id, profile);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ProfileCreateDTO profile,
                                          @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token,ProfileRole.ROLE_ADMIN);
        profileService.update(dto.getId(),profile);
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
