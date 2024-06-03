package dasturlash.uz.service;


import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.dto.article.ArticleStatusDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.util.SecurityUtil;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.nio.file.Paths.get;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleTypesService articleTypesService;
    @Autowired
    private RegionService regionService;

    public ArticleRequestDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        RegionEntity regionEntity=regionService.getId(articleRequestDTO.getRegionId());
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(articleRequestDTO.getCategoryId());
        if (categoryEntity.isEmpty()){
            throw new AppBadException("Category not found");
        }
//        Integer moderatorId= SecurityUtil.getProfileId();

        ProfileEntity moderator=SecurityUtil.getProfile();

        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleRequestDTO.getTitle());
        article.setDescription(articleRequestDTO.getDescription());
        article.setContent(articleRequestDTO.getContent());
        article.setImageId(articleRequestDTO.getImageId());

        article.setRegionId(articleRequestDTO.getRegionId());
        article.setCategoryId(articleRequestDTO.getCategoryId());
        article.setModeratorId(article.getModeratorId());
        articleRepository.save(article);
//        articleTypesService.create(article.getId(),article.getArticleType());
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
        articleDTO.setArticleType(entity.getArticleType());
        articleDTO.setRole(entity.getRole());
        return articleDTO;
    }

    public ArticleEntity updateArticle(UUID id, ArticleRequestDTO articleUpdateDTO) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new IllegalArgumentException("Article does not exists");
        }

        ArticleEntity article = optionalArticle.get();

        if (!categoryRepository.existsById(articleUpdateDTO.getCategoryId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        article.setTitle(articleUpdateDTO.getTitle());
        article.setDescription(articleUpdateDTO.getDescription());
        article.setContent(articleUpdateDTO.getContent());
        article.setSharedCount(articleUpdateDTO.getSharedCount());
        article.setImageId(articleUpdateDTO.getImageId());
        article.setRegionId(articleUpdateDTO.getRegionId());
        article.setCategoryId(articleUpdateDTO.getCategoryId());
        article.setStatus(ArticleStatus.NOT_PUBLISHED);
        return articleRepository.save(article);
    }

    public void deleteArticle(UUID id) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            throw new IllegalArgumentException("Article does not exists");
        }
        ArticleEntity article = optionalArticle.get();
        if (!categoryRepository.existsById(article.getCategoryId())) {
            throw new IllegalArgumentException("Category does not exist");
        }
        articleRepository.delete(article);
    }

    public ArticleEntity changeByStatus(UUID id, ArticleStatusDTO statusDTO) {
        ArticleEntity article = articleRepository.findById(id).get();
        article.setStatus(statusDTO.getStatus());
        return articleRepository.save(article);
    }

    public List<ArticleRequestDTO> TypeById(List<String> type) {
        return articleRepository.findTypeIdOrder(type);
    }
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


