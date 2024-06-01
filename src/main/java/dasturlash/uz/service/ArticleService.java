package dasturlash.uz.service;


import dasturlash.uz.dto.response.ArticleRequestDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RegionService regionService;

    public ArticleRequestDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleRequestDTO.getTitle());
        article.setDescription(articleRequestDTO.getDescription());
        article.setContent(articleRequestDTO.getContent());
        article.setImageId(articleRequestDTO.getImageId());
        article.setRegionId(articleRequestDTO.getRegionId());
        article.setCategoryId(articleRequestDTO.getCategoryId());
        article.setArticleType(articleRequestDTO.getArticleType());
        articleRepository.save(article);
        return articleToDTO(article);
    }

    private ArticleRequestDTO articleToDTO(ArticleEntity entity) {
        ArticleRequestDTO articleDTO = new ArticleRequestDTO();
        articleDTO.setTitle(entity.getTitle());
        articleDTO.setDescription(entity.getDescription());
        articleDTO.setContent(entity.getContent());
        articleDTO.setImageId(entity.getImageId());
        articleDTO.setRegionId(entity.getRegionId());
        articleDTO.setCategoryId(entity.getCategoryId());
        return articleDTO;
    }
   /* public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(UUID id) {
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public Article updateArticle(UUID id, Article articleDetails) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
        article.setTitle(articleDetails.getTitle());
        article.setDescription(articleDetails.getDescription());
        article.setContent(articleDetails.getContent());
        article.setSharedCount(articleDetails.getSharedCount());
        article.setImageId(articleDetails.getImageId());
        article.setRegionId(articleDetails.getRegionId());
        article.setCategoryId(articleDetails.getCategoryId());
        article.setModeratorId(articleDetails.getModeratorId());
        article.setPublisherId(articleDetails.getPublisherId());
        article.setStatus(articleDetails.getStatus());
        article.setCreatedDate(articleDetails.getCreatedDate());
        article.setPublishedDate(articleDetails.getPublishedDate());
        article.setVisible(articleDetails.isVisible());
        article.setViewCount(articleDetails.getViewCount());
        article.setTypes(articleDetails.getTypes());
        return articleRepository.save(article);
    }

    public void deleteArticle(UUID id) {
        articleRepository.deleteById(id);
    }*/
}

