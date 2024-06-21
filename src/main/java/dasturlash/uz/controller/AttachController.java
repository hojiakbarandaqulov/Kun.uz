package dasturlash.uz.controller;

import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("/attach")
public class AttachController {
    private final AttachService attachService;

    public AttachController(AttachService attachService) {
        this.attachService = attachService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO attachDTO = attachService.saveAttach(file);
        return ResponseEntity.ok().body(attachDTO);
    }
    /*@GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return this.attachService.loadImage(fileName);
    }*/

    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return this.attachService.load(fileName);
    }

    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }


    @GetMapping("/download/{fineName}")
    public ResponseEntity download(@PathVariable("fineName") String fileName) {
        return attachService.download(fileName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/pagination")
    public ResponseEntity<PageImpl<AttachDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageImpl<AttachDTO> response = attachService.getAttachPagination(page - 1, size);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        Boolean delete = attachService.delete(id);
        return ResponseEntity.ok().body(delete);
    }

}
