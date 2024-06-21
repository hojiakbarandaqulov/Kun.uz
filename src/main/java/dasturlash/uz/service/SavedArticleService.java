package dasturlash.uz.service;

import dasturlash.uz.dto.AttachDTO;
import dasturlash.uz.dto.SavedArticleDTO;
import dasturlash.uz.dto.article.ArticleDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.AttachEntity;
import dasturlash.uz.entity.SavedArticleEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.AttachRepository;
import dasturlash.uz.repository.SavedArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SavedArticleService {
    private SavedArticleRepository savedArticleRepository;
    private ArticleRepository articleRepository;
    private AttachRepository attachRepository;
   /* public SavedArticleService(SavedArticleRepository savedArticleRepository, ArticleRepository articleRepository, AttachRepository attachRepository) {
        this.savedArticleRepository = savedArticleRepository;
        this.articleRepository = articleRepository;
        this.attachRepository = attachRepository;
    }*/

    @Transactional
    public SavedArticleEntity create(String articleId) {
        SavedArticleEntity savedArticle = new SavedArticleEntity();
        savedArticle.setArticleId(articleId);
        return savedArticleRepository.save(savedArticle);
    }

    public Boolean delete(String articleId) {
        Optional<SavedArticleEntity> byArticleId = savedArticleRepository.findByArticleId(articleId);
        if (byArticleId.isEmpty()){
            throw  new AppBadException("ArticleId not found");
        }
        return true;
    }

    @Transactional(readOnly = true)
    public List<SavedArticleDTO> getProfileSavedArticles(Integer profileId) {
        List<SavedArticleEntity> savedArticles = savedArticleRepository.findByProfileId(profileId);
        return savedArticles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public SavedArticleDTO convertToDTO(SavedArticleEntity savedArticleEntity) {
        ArticleEntity articleEntity = (ArticleEntity) articleRepository.findById(savedArticleEntity.getArticleId());
        if (articleEntity == null) {
            return null;
        }

        AttachEntity attachEntity = attachRepository.findById(articleEntity.getImageId()).orElse(null);

        return new SavedArticleDTO(
                savedArticleEntity.getId(),
                new ArticleDTO(
                        articleEntity.getId(),
                        articleEntity.getTitle(),
                        articleEntity.getDescription(),
                        new AttachDTO(
                                attachEntity.getId(),
                                generateImageUrl(attachEntity.getId())
                        )
                )
        );
    }

    private String generateImageUrl(String attachId) {
        return "https://asturlash.us/uploads/" + attachId;
    }
}
