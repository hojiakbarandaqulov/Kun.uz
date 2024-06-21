package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.create.CategoryCreateDTO;
//import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.mapper.CategoryMapper;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO create(CategoryCreateDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryEntity.setNameRu(categoryDTO.getNameRu());
        categoryEntity.setNameEn(categoryDTO.getNameEn());
        categoryRepository.save(categoryEntity);

        return categoryToDTO(categoryEntity);
    }

    public Boolean update(Integer id, CategoryCreateDTO dto) {
        CategoryEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        categoryRepository.save(entity);
        return true;
    }

    public CategoryDTO categoryToDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setOrderNumber(categoryEntity.getOrderNumber());
        categoryDTO.setNameUz(categoryDTO.getNameUz());
        categoryDTO.setNameRu(categoryEntity.getNameRu());
        categoryDTO.setNameEn(categoryDTO.getNameEn());
        return categoryDTO;
    }

    private CategoryEntity get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
    public Boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return true;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> entity = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity region : entity) {
            dtoList.add(categoryToDTO(region));
        }
        return dtoList;
    }

    public List<CategoryDTO> getAllByLanguage(Language lang) {
        List<CategoryMapper> mapperList = categoryRepository.findByLanguage(lang.name());
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryMapper entity : mapperList) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CategoryEntity getId(Integer id){
        Optional<CategoryEntity> optional=categoryRepository.findById(id);
        if (optional.isEmpty()){
            throw new IllegalArgumentException("Category not found");
        }
        return optional.get();
    }
    public RegionDTO getRegion(Integer id, Language lang){
        CategoryEntity region=get(id);
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        switch (lang){
            case UZ -> dto.setNameUz(region.getNameUz());
            case RU -> dto.setNameRu(region.getNameRu());
            default -> dto.setNameEn(region.getNameEn());
        }
        return dto;
    }
}
