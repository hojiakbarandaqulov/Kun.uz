package dasturlash.uz.service;

import dasturlash.uz.dto.CommentDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.create.CommentCreateDTO;
import dasturlash.uz.dto.update.CommentUpdateDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CommentEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.Comment;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    public CommentCreateDTO create(CommentCreateDTO comment) {
        CommentEntity commentEntity = new CommentEntity();
        ArticleEntity entity = get(comment.getArticleId());
        commentEntity.setComment(comment.getComment());
        commentEntity.setArticle(entity);
        commentEntity.setReplyId(comment.getReplyId());
        commentRepository.save(commentEntity);
        return toDTO(commentEntity);
    }

    private CommentCreateDTO toDTO(CommentEntity commentEntity) {
        CommentCreateDTO commentCreateDTO = new CommentCreateDTO();
        commentCreateDTO.setComment(commentEntity.getComment());
        commentCreateDTO.setArticleId(String.valueOf(commentEntity.getArticle()));
        commentCreateDTO.setReplyId(commentEntity.getReplyId());
        return commentCreateDTO;
    }

    private CommentDTO toDTOComment(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(commentEntity.getComment());
        commentDTO.setArticleId(String.valueOf(commentEntity.getArticle()));
        commentDTO.setReplyId(commentEntity.getReplyId());
        commentDTO.setComment(commentEntity.getComment());
        commentDTO.setProfileId(commentEntity.getProfile());
        return commentDTO;
    }

    public ArticleEntity get(String id) {
        return (ArticleEntity) articleRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }

    public Boolean update(Integer id, CommentUpdateDTO comment) {
        CommentEntity entity = getComment(id);
        entity.setComment(comment.getComment());
        entity.setArticle(get(comment.getArticleId()));
        commentRepository.save(entity);
        return true;
    }

    public CommentEntity getComment(Integer id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Comment not found");
        });
    }

    public Boolean delete(Integer id) {
        CommentEntity commentEntity = getComment(id);
        commentRepository.delete(commentEntity);
        return true;
    }

    public List<CommentDTO> getCommentsByArticleId(String articleId) {
        List<CommentEntity> comments = commentRepository.findByArticleId(articleId);
        List<CommentDTO> commentDto = new LinkedList<>();
        for (CommentEntity comment : comments) {
            CommentDTO dto = convertToDto(comment);
            commentDto.add(dto);
        }
        return commentDto;
    }

    private CommentDTO convertToDto(CommentEntity entity) {
        CommentDTO commentDto = new CommentDTO();
        commentDto.setId(String.valueOf(entity.getId()));
        commentDto.setCreatedDate(entity.getCreatedDate());
        commentDto.setUpdatedDate(entity.getUpdatedDate());

        // Profile ma'lumotlarini ProfileDto ga aylantirish
        ProfileDTO profileDto = new ProfileDTO();
        profileDto.setId(entity.getId());
        profileDto.setName(entity.getProfile().getName());
        profileDto.setSurname(entity.getProfile().getSurname());

        // ProfileDto ni CommentDto ga qo'shish
        commentDto.setProfile(profileDto);

        return commentDto;
    }

    public PageImpl<CommentDTO> getPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CommentEntity> comments = commentRepository.findAll(pageable);

        List<CommentDTO> commentDto = new LinkedList<>();
        for (CommentEntity comment : comments) {
            CommentDTO dto = toDTOComment(comment);
            commentDto.add(dto);
        }
        return new PageImpl<>(commentDto, pageable, comments.getTotalElements());
    }
}
