package dasturlash.uz.service;


import dasturlash.uz.dto.article.ArticleDTO;
import dasturlash.uz.dto.create.ArticleCreateDTO;
import dasturlash.uz.dto.response.ArticleRequestDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.mapper.ArticleMapper;
import dasturlash.uz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegionService regionService;
  /*  public ArticleDTO createArticle(ArticleCreateDTO article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(article.getTitle());
        entity.setDescription(article.getDescription());
        entity.setContent(article.getContent());
        entity.setImageId(article.getImageId());
        entity.setRegionId(article.getRegionId());
        entity.setCategoryId(article.getCategoryId());
        entity.set(article.getArticleType());
        articleRepository.save(entity);
        return articleToDTO(entity);
    }*/

    public ArticleRequestDTO create(ArticleRequestDTO dto) {
        // check
//        ProfileEntity moderator = profileService.get(moderId);
        /*RegionEntity region = regionService.getId(dto.getRegionId());
        CategoryEntity category = categoryService.getId(dto.getCategoryId());*/
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
//        entity.setModeratorId(moderId);
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        // type
        articleRepository.save(entity);
        return articleToDTO(entity);
    }

    private ArticleRequestDTO articleToDTO(ArticleEntity entity) {
        ArticleRequestDTO articleDTO = new ArticleRequestDTO();
//        articleDTO.setId(entity.getId());
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

