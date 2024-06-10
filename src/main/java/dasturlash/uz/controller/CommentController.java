package dasturlash.uz.controller;

import dasturlash.uz.dto.CommentDTO;
import dasturlash.uz.dto.create.CommentCreateDTO;
import dasturlash.uz.dto.update.CommentUpdateDTO;
import dasturlash.uz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/create")
    public ResponseEntity<CommentCreateDTO> addComment(@RequestBody CommentCreateDTO comment) {
        CommentCreateDTO commentCreateDTO = commentService.create(comment);
        return ResponseEntity.ok().body(commentCreateDTO);
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateCommit(@PathVariable("id")Integer id,
                                                @RequestBody CommentUpdateDTO comment){
        Boolean result = commentService.update(id, comment);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable("id")Integer id){
        Boolean response = commentService.delete(id);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{articleId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByArticleId(@PathVariable("articleId") String articleId) {
        List<CommentDTO> comment = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(comment);
    }
}
