package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryCreateDTO;
import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.mapper.CategoryMapper;
import dasturlash.uz.repository.CategoryRepository;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDTO create(CategoryCreateDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryEntity.setNameRu(categoryDTO.getNameRu());
        categoryEntity.setNameEn(categoryDTO.getNameEn());
        categoryRepository.save(categoryEntity);
        return toCategoryDTO(categoryEntity);
    }

    public CategoryDTO toCategoryDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setOrderNumber(categoryEntity.getOrderNumber());
        categoryDTO.setNameUz(categoryDTO.getNameUz());
        categoryDTO.setNameRu(categoryEntity.getNameRu());
        categoryDTO.setNameEn(categoryDTO.getNameEn());
        categoryDTO.setVisible(categoryEntity.getVisible());
        categoryDTO.setCreatedDate(categoryEntity.getCreatedDate());
        return categoryDTO;
    }
    public Boolean update(Integer id, CategoryDTO dto) {
        CategoryEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        categoryRepository.save(entity);
        return true;
    }

    private CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    public Boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return true;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> entity = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity region : entity) {
            dtoList.add(toCategoryDTO(region));
        }
        return dtoList;
    }
    /*   public List<CategoryDTO> getByLanguage(Language language) {
           Iterable<CategoryEntity> entity = categoryRepository.findByLanguage(language);
           List<CategoryDTO> dtoList = new LinkedList<>();
           for (CategoryEntity region : entity) {
               CategoryDTO categoryDTO = new CategoryDTO();
               switch (language){
                   case uz -> categoryDTO.setNameUz(region.getNameUz());
                   case ru -> categoryDTO.setNameRu(region.getNameRu());
                   case en -> categoryDTO.setNameEn(region.getNameEn());
               }
              *//* if (language.equals(nameUz)) {
                categoryDTO.setId(region.getId());
                categoryDTO.setNameUz(region.getNameUz());
                dtoList.add(categoryDTO);
            }
            if (language.equals(nameRu)) {
                categoryDTO.setId(region.getId());
                categoryDTO.setNameRu(region.getNameRu());
                dtoList.add(categoryDTO);
            }
            if (language.equals(nameEn)) {
                categoryDTO.setId(region.getId());
                categoryDTO.setNameEn(region.getNameEn());
                dtoList.add(categoryDTO);
            }*//*
        }
        return dtoList;*/
    public List<CategoryDTO> getAllByLanguage(Language language) {
        List<CategoryMapper> categoryMapperList = categoryRepository.findByLanguage(language.name());
        List<CategoryDTO> categoryList = new LinkedList<>();
        for (CategoryMapper entity : categoryMapperList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(entity.getId());
            categoryDTO.setName(entity.getName());
            categoryList.add(categoryDTO);
        }
        return categoryList;
    }
}

