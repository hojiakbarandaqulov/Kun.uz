package dasturlash.uz.service;


import dasturlash.uz.dto.article.ArticleRequestDTO;

import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ArticleStatus;

import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.*;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.nio.file.Paths.get;

import dasturlash.uz.repository.ArticleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final ArticleTypesService articleTypesService;

    private final ArticleTypesRepository articleTypesRepository;

    @Autowired
    public ArticleService(ArticleTypesRepository articleTypesRepository, ArticleTypesService articleTypesService, ArticleRepository articleRepository) {
        this.articleTypesRepository = articleTypesRepository;
        this.articleTypesService = articleTypesService;
        this.articleRepository = articleRepository;
    }

    public ArticleRequestDTO createArticle(ArticleRequestDTO articleRequestDTO) {
        ProfileEntity moderator = SecurityUtil.getProfile();
        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleRequestDTO.getTitle());
        article.setDescription(articleRequestDTO.getDescription());
        article.setContent(articleRequestDTO.getContent());
        article.setImageId(articleRequestDTO.getImageId());

        article.setRegionId(articleRequestDTO.getRegionId());
        article.setCategoryId(articleRequestDTO.getCategoryId());
        article.setModeratorId(article.getModeratorId());
        articleRepository.save(article);
        articleTypesService.create(article.getId(), articleRequestDTO.getArticleType());
        return articleToDTO(article);
    }

    private ArticleRequestDTO articleToDTO(ArticleEntity entity) {
        ArticleRequestDTO articleDTO = new ArticleRequestDTO();
        articleDTO.setTitle(entity.getTitle());
        articleDTO.setDescription(entity.getDescription());
        articleDTO.setContent(entity.getContent());
        articleDTO.setImageId(entity.getImageId());
        return articleDTO;
    }

    /*public ArticleEntity updateArticle(String id, ArticleRequestDTO articleUpdateDTO) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(UUID.fromString(id));
=======
<<<<<<< HEAD
        return articleDTO;
    }

    public ArticleEntity updateArticle(String id, ArticleRequestDTO articleUpdateDTO) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(UUID.fromString(id));
=======
>>>>>>> 2e61bf5e2689c94a813a385e9a9afc0767de9c34
        articleDTO.setRegionId(entity.getRegionId());
        articleDTO.setCategoryId(entity.getCategoryId());
        articleDTO.setArticleType(entity.getArticleType());
        articleDTO.setRole(entity.getRole());
        return articleDTO;
    }*/

    public ArticleRequestDTO update(String articleId, ArticleRequestDTO dto) {
        ArticleEntity entity = get(articleId);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);

        articleTypesService.merge(articleId, dto.getArticleType());
        return toDTO(entity);
    }

    private ArticleRequestDTO toDTO(ArticleEntity entity) {
        ArticleRequestDTO dto = new ArticleRequestDTO();
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setImageId(entity.getImageId());
        dto.setRegionId(entity.getRegionId());
        dto.setCategoryId(entity.getCategoryId());
        dto.setStatus(ArticleStatus.PUBLISHED);
        return dto;
    }

    public void deleteArticle(String id) {
        ArticleEntity article = articleRepository.findById(id);
        article.setVisible(false);
        articleRepository.save(article);
    }

    public void changeByStatus(String id) {
        ArticleEntity article = articleRepository.findById(id);
        article.setStatus(ArticleStatus.PUBLISHED);
        articleRepository.save(article);
    }

    public ArticleEntity get(String id) {
        return (ArticleEntity) articleRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }

    public List<ArticleRequestDTO> TypeById(Integer type) {
        List<ArticleEntity> top5By = articleRepository.findTop5By(type);
        List<ArticleRequestDTO> list = new ArrayList<>();
        for (ArticleEntity articleEntity : top5By) {
            ArticleRequestDTO dto = toDTO(articleEntity);
            list.add(dto);
        }
        return list;
    }
}
/*
        return list;
    public ArticleEntity updateArticle(UUID id,ArticleRequestDTO articleUpdateDTO) {
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
*/

/*

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
*/


//    public List<ArticleRequestDTO> TypeById(List<String> type) {
//        return articleRepository.findTypeIdOrder(type);
//    }

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
