package dasturlash.uz.service;


import dasturlash.uz.dto.ArticleDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.dto.create.ArticleCreateDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO createArticle(ArticleCreateDTO article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setDescription(article.getDescription());
        entity.setContent(article.getContent());
        entity.setImageId(article.getImageId());
        entity.setRegionId(article.getRegionId());
//        entity.setCategory(article.getCategory());
        articleRepository.save(entity);
        return articleToDTO(entity);
    }

    private ArticleDTO articleToDTO(ArticleEntity entity) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(entity.getId());
        articleDTO.setDescription(entity.getDescription());
        articleDTO.setContent(entity.getContent());
        articleDTO.setImageId(entity.getImageId());
        articleDTO.setRegionId(entity.getRegionId());
//        articleDTO.setCategory(entity.getCategory());
        articleDTO.setModeratorId(entity.getModeratorId());
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

