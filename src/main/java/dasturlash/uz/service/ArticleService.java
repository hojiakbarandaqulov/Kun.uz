package dasturlash.uz.service;


import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.article.ArticleCreateDTO;
import dasturlash.uz.dto.article.ArticleDTO;
import dasturlash.uz.dto.article.ArticleRequestDTO;

import dasturlash.uz.entity.*;
import dasturlash.uz.enums.ArticleStatus;

import dasturlash.uz.enums.Language;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.ArticleShortInfoMapper;
import dasturlash.uz.repository.*;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import dasturlash.uz.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleTypesService articleTypesService;
    private final RegionRepository regionRepository;
    private final CategoryRepository categoryRepository;
    private final AttachService attachService;
    private final CategoryService categoryService;
    private final ArticleLikeRepository articleLikeRepository;

    public ArticleService(ArticleTypesRepository articleTypesRepository, ArticleTypesService articleTypesService, ArticleRepository articleRepository, RegionRepository regionRepository, CategoryRepository categoryRepository, AttachService attachService, CategoryService categoryService, ArticleLikeRepository articleLikeRepository) {
        this.articleTypesService = articleTypesService;
        this.articleRepository = articleRepository;
        this.regionRepository = regionRepository;
        this.categoryRepository = categoryRepository;
        this.attachService = attachService;
        this.categoryService = categoryService;
        this.articleLikeRepository = articleLikeRepository;
    }

    public ArticleRequestDTO createArticle(ArticleCreateDTO createDto) {
        ProfileEntity moderator = SecurityUtil.getProfile();

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(createDto.getTitle());
        articleEntity.setDescription(createDto.getDescription());
        articleEntity.setContent(createDto.getContent());
        articleEntity.setImageId(createDto.getImageId());
        articleEntity.setRegionId(createDto.getRegionId());
        articleEntity.setCategoryId(createDto.getCategoryId());
        articleEntity.setModeratorId(moderator.getId());
        articleRepository.save(articleEntity);
        articleTypesService.create(articleEntity.getId(), createDto.getTypesList());

        return toDTO(articleEntity);
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
        entity.setStatus(ArticleStatus.PUBLISHED);
        entity.setViewCount(1);
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
        ArticleEntity entity=get(id);
        entity.setVisible(false);
        articleRepository.save(entity);
    }

    public void changeByStatus(String id) {
        ArticleEntity entity=get(id);
        entity.setStatus(ArticleStatus.PUBLISHED);
        entity.setPublishedDate(LocalDateTime.now());
        articleRepository.save(entity);
    }

    public List<ArticleRequestDTO> getLast5ByTypes(Integer typesId) {
            List<ArticleShortInfoMapper> mapperList = articleRepository.getByTypesId(typesId,5);
            List<ArticleRequestDTO> dtoList = new LinkedList<>();
            for (ArticleShortInfoMapper mapper : mapperList) {
                ArticleRequestDTO dto = new ArticleRequestDTO();
                dto.setId(mapper.getId());
                dto.setTitle(mapper.getTitle());
                dto.setDescription(mapper.getDescription());
                dto.setPublishDate(mapper.getPublishedDate());
                dto.setImage(attachService.getDTOWithURL(mapper.getImageId()));
                dtoList.add(dto);
            }
            return dtoList;
        }


    public ArticleEntity get(String id) {
        return (ArticleEntity) articleRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Article not found");
        });
    }

    public List<ArticleRequestDTO> getLast3ByTypes(Integer typesId) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getByTypesId(typesId, 3);
        return mapperList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public ArticleRequestDTO toDTO(ArticleShortInfoMapper mapper) {
        ArticleRequestDTO dto = new ArticleRequestDTO();
        dto.setId(mapper.getId());
        dto.setTitle(mapper.getTitle());
        dto.setDescription(mapper.getDescription());
        dto.setPublishDate(mapper.getPublishedDate());
        dto.setImage(attachService.getDTOWithURL(mapper.getImageId()));
        return dto;
    }

    public List<ArticleRequestDTO> getLast8ByTypes(List<String> id) {
        List<ArticleShortInfoMapper> mapperList = articleRepository.getLast8(id);
        return mapperList.stream().map(this:: toDTO).collect(Collectors.toList());
    }

    public List<ArticleRequestDTO> getArticleByTypes(String id) {
        ArticleEntity articleEntity = get(id);
        List<ArticleRequestDTO> list = new LinkedList<>();
        ArticleRequestDTO articleDTO = new ArticleRequestDTO();
        articleDTO.setTitle(articleEntity.getTitle());
        articleDTO.setDescription(articleEntity.getDescription());
        articleDTO.setContent(articleEntity.getContent());
        articleDTO.setImageId(articleEntity.getImageId());
        articleDTO.setRegionId(articleEntity.getRegionId());
        articleDTO.setCategoryId(articleEntity.getCategoryId());
        articleDTO.setStatus(articleEntity.getStatus());
        list.add(articleDTO);
        return list;
    }

    public List<ArticleRequestDTO> getLast4ByTypes(Integer id) {
        List<Object[]> object = articleRepository.findLast4ByTypeAndExceptArticleId(id);
        return null;
    }

    public List<ArticleEntity> getRead4Articles() {
        List<ArticleEntity> read4Articles = articleRepository.getRead4Articles();
        return null;
    }

    public List<ArticleEntity> findLast4ByTagName(String tagName) {
        List<ArticleEntity> read4Articles = articleRepository.findLast4ByTagName(tagName);
        return null;
    }
    public List<ArticleEntity> findLast5ByTypesAndRegionKey(TypesEntity types, Integer regionId) {
        articleRepository.findLast5ByTypesAndRegionKey(types,regionId);
        return null;
    }

    public PageImpl<RegionDTO> findRegionId(Integer regionId, Integer page, Integer size) {
        Optional<RegionEntity> byId = regionRepository.findById(regionId);
        if (byId.isEmpty()) {
            throw new AppBadException("Region  id not found");
        }
        Sort sort=Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<RegionEntity> all = regionRepository.findAll(pageable);
        List<RegionDTO>list=new LinkedList<>();
        for (RegionEntity entity:all.getContent()){
            RegionDTO dto=new RegionDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setNameUz(entity.getNameUz());
            dto.setNameEn(entity.getNameEn());
            dto.setNameRu(entity.getNameRu());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setVisible(entity.getVisible());
            list.add(dto);
        }
        Long total = all.getTotalElements();
        return new PageImpl<RegionDTO>(list, pageable,total);
    }

    public Optional<CategoryEntity> getCategoryId(Integer id) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()){
            throw new AppBadException("Category not found");
        }
        return byId;
    }


   /* public Page<CategoryDTO> findByCategoryId(Integer page, Integer size, Integer categoryId) {
        Page<ArticleEntity> byId = articleRepository.findByCategoryId(categoryId);
        if (byId.isEmpty()){
            throw new AppBadException("Article not found");
        }
        Sort sort=Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<CategoryEntity> all = categoryRepository.findAll(pageable);
        List<CategoryDTO>list=new LinkedList<>();
        for (CategoryEntity entity:all.getContent()){
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setNameUz(entity.getNameUz());
            dto.setNameEn(entity.getNameEn());
            dto.setNameRu(entity.getNameRu());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setVisible(entity.getVisible());
            list.add(dto);
        }
        Long total = all.getTotalElements();
        return new PageImpl<CategoryDTO>(list, pageable,total);*/

 /*   public ArticleFullInfo getArticleByIdAndLang(Long id, String lang) {
        Article article = articleRepository.findByIdAndLang(id, lang)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
        return new ArticleFullInfo(article);
    }*/
}



