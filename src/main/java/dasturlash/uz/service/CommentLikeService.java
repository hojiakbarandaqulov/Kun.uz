package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleLikeEntity;
import dasturlash.uz.entity.CommentLikeEntity;
import dasturlash.uz.enums.EmotionStatus;
import dasturlash.uz.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    
    public CommentLikeService(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    public boolean like(String commentId, Integer profileId) {
        makeEmotion(commentId, profileId, EmotionStatus.LIKE);
        return true;
    }

    public boolean dislike(String commentId, Integer profileId) {
        makeEmotion(commentId, profileId, EmotionStatus.DISLIKE);
        return true;
    }

    public boolean delete(String commentId, Integer profileId) {
        commentLikeRepository.delete(commentId, profileId);
        return true;
    }

    private void makeEmotion(String commentId, Integer profileId, EmotionStatus status) {
        Optional<CommentLikeEntity> optional = commentLikeRepository.findByCommentIdAndProfileId(commentId, profileId);
        if (optional.isEmpty()) {
            CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
            commentLikeEntity.setCommentId(commentId);
//            commentLikeEntity.setProfileId(profileId);
            commentLikeEntity.setStatus(status);
            commentLikeEntity.setCreatedDate(LocalDateTime.now());
            commentLikeRepository.save(commentLikeEntity);
        } else {
            commentLikeRepository.update(status, commentId, profileId);
        }
    }
}
