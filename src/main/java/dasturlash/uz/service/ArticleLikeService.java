package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleLikeEntity;
import dasturlash.uz.enums.EmotionStatus;
import dasturlash.uz.repository.ArticleLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleLikeService {

    private final ArticleLikeRepository articleLikeRepository;

    public ArticleLikeService(ArticleLikeRepository articleLikeRepository) {
        this.articleLikeRepository = articleLikeRepository;
    }

    public boolean like(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, EmotionStatus.LIKE);
        return true;
    }

    public boolean dislike(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, EmotionStatus.DISLIKE);
        return true;
    }

    public boolean delete(String articleId, Integer profileId) {
        articleLikeRepository.delete(articleId, profileId);
        return true;
    }

    private void makeEmotion(String articleId, Integer profileId, EmotionStatus status) {
        Optional<ArticleLikeEntity> optional = articleLikeRepository
                .findByArticleIdAndProfileId(articleId, profileId);
        if (optional.isEmpty()) {
            ArticleLikeEntity entity = new ArticleLikeEntity();
            entity.setArticleId(articleId);
            entity.setProfileId(profileId);
            entity.setStatus(status);
            articleLikeRepository.save(entity);
        } else {
            articleLikeRepository.update(status, articleId, profileId);
        }
    }
}
