package dasturlash.uz.controller;

import dasturlash.uz.dto.*;
import dasturlash.uz.dto.auth.JwtDTO;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.ProfileService;
import dasturlash.uz.util.JwtUtil;
import dasturlash.uz.util.SecurityUtil;
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
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileDTO,
                                             @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_USER);
        ProfileDTO response = profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/current")
    public ResponseEntity<Boolean> updateUser(@RequestBody ProfileUpdateDTO profile,
                                              @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        profileService.updateUser(dto.getId(), profile);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@Valid @RequestBody ProfileCreateDTO profile,
                                          @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token,ProfileRole.ROLE_ADMIN);
        profileService.update(dto.getId(),profile);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/profilePagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size,
                                                       @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        PageImpl<ProfileDTO> typeList = profileService.getAllPagination(page - 1, size);
        return ResponseEntity.ok().body(typeList);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        profileService.deleteId(dto.getId());
        return ResponseEntity.ok().body(true);
    }
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProfileFilterDTO filter,
                                                               @RequestHeader("Authorization") String token) {
        JwtDTO dto = SecurityUtil.getJwtDTO(token);
        PageImpl<ProfileDTO> studentDTOList = profileService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }
}
