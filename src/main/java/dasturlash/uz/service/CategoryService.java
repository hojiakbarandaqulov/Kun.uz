package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
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
    private String nameUz = String.valueOf(Language.nameUz);
    private String nameRu = String.valueOf(Language.nameRu);
    private String nameEn = String.valueOf(Language.nameEn);

    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryEntity.setNameRu(categoryDTO.getNameRu());
        categoryEntity.setNameEn(categoryDTO.getNameEn());
        categoryRepository.save(categoryEntity);
        categoryDTO.setId(categoryEntity.getId());
        return categoryDTO;
    }
   /* public RegionDTO create(RegionDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setOrderNumber(regionDTO.getOrderNumber());
        regionEntity.setNameUz(regionDTO.getNameUz());
        regionEntity.setNameRu(regionDTO.getNameRu());
        regionEntity.setNameEn(regionDTO.getNameEn());
        regionEntity.setVisible(regionDTO.getVisible());
        regionEntity.setCreatedDate(regionDTO.getCreatedDate());
        regionRepository.save(regionEntity);
        regionDTO.setId(regionEntity.getId());
        return regionDTO;
    }*/

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
        CategoryEntity entity = get(id);
        categoryRepository.delete(entity);
        return true;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> entity = categoryRepository.findAll();
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity region : entity) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(region.getId());
            categoryDTO.setNameUz(region.getNameUz());
            categoryDTO.setNameRu(region.getNameRu());
            categoryDTO.setNameEn(region.getNameEn());
            categoryDTO.setVisible(categoryDTO.getVisible());
            categoryDTO.setCreatedDate(region.getCreatedDate());
            dtoList.add(categoryDTO);
        }
        return dtoList;
    }
    public List<CategoryDTO> getByLanguage(String language) {
        Iterable<CategoryEntity> entity = categoryRepository.findByLanguage(language);
        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity region : entity) {
            CategoryDTO categoryDTO = new CategoryDTO();
            if (language.equals(nameUz)) {
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
            }
        }
        return dtoList;
    }

}
