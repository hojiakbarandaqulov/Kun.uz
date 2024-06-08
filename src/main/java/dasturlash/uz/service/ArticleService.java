package dasturlash.uz.service;


import dasturlash.uz.dto.article.ArticleCreateDTO;
import dasturlash.uz.dto.article.ArticleRequestDTO;

import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ArticleStatus;

import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.*;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dasturlash.uz.repository.ArticleRepository;

import java.util.LinkedList;
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

    public ArticleRequestDTO createArticle(ArticleCreateDTO articleCreateDTO) {
        ProfileEntity moderator = SecurityUtil.getProfile();
        ArticleEntity article = new ArticleEntity();
        article.setTitle(articleCreateDTO.getTitle());
        article.setDescription(articleCreateDTO.getDescription());
        article.setContent(articleCreateDTO.getContent());
        article.setImageId(articleCreateDTO.getImageId());

        article.setRegionId(articleCreateDTO.getRegionId());
        article.setCategoryId(articleCreateDTO.getCategoryId());
        article.setModeratorId(moderator.getId());
        articleRepository.save(article);
        articleTypesService.create(article.getId(), articleCreateDTO.getTypesList());
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

    public ArticleRequestDTO update(String articleId, ArticleCreateDTO dto) {
        ArticleEntity entity = get(articleId);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        articleRepository.save(entity);

        articleTypesService.merge(articleId, dto.getTypesList());
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

    public List<ArticleRequestDTO> TypeById(Integer type) {
        List<ArticleEntity> top5By = articleRepository.findTop5By(type);
        List<ArticleRequestDTO> list = new LinkedList<>();
        for (ArticleEntity articleEntity : top5By) {
            ArticleRequestDTO dto = toDTO(articleEntity);
            list.add(dto);
        }
        return list;
    }

    public ArticleEntity get(String id) {
        return (ArticleEntity) articleRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }
}



