package dasturlash.uz.service;

import dasturlash.uz.dto.create.TypesCreatedDto;
import dasturlash.uz.dto.TypesDTO;
import dasturlash.uz.entity.TypesEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.mapper.TypesMapper;
import dasturlash.uz.repository.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TypesService {
    @Autowired
    private TypesRepository typesRepository;

    public TypesDTO create(TypesCreatedDto typesDTO) {
        TypesEntity typesEntity = new TypesEntity();
        typesEntity.setOrderNumber(typesDTO.getOrderNumber());
        typesEntity.setNameUz(typesDTO.getNameUz());
        typesEntity.setNameRu(typesDTO.getNameRu());
        typesEntity.setNameEn(typesDTO.getNameEn());
        typesRepository.save(typesEntity);
        return typesToDTO(typesEntity);
    }

    public Boolean update(Integer id, TypesDTO dto) {
        TypesEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typesRepository.save(entity);
        return true;
    }

    public TypesDTO typesToDTO(TypesEntity typesEntity) {
        TypesDTO dto = new TypesDTO();
        dto.setId(typesEntity.getId());
        dto.setOrderNumber(typesEntity.getOrderNumber());
        dto.setNameUz(typesEntity.getNameUz());
        dto.setNameRu(typesEntity.getNameRu());
        dto.setNameEn(typesEntity.getNameEn());
        dto.setVisible(typesEntity.getVisible());
        dto.setCreatedDate(typesEntity.getCreatedDate());
        return dto;
    }

    private TypesEntity get(Integer id) {
        return typesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Types not found"));
    }

    public Boolean delete(Integer id) {
        typesRepository.deleteById(id);
        return true;
    }

    public PageImpl<TypesDTO> getAllPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TypesEntity> pageObj = typesRepository.findAll(pageable);

        List<TypesDTO> dtoList = new LinkedList<>();
        for (TypesEntity types : pageObj.getContent()) {
            TypesDTO typesDTO = new TypesDTO();
            typesDTO.setId(types.getId());
            typesDTO.setNameUz(types.getNameUz());
            typesDTO.setNameRu(types.getNameRu());
            typesDTO.setNameEn(types.getNameEn());
            typesDTO.setOrderNumber(types.getOrderNumber());
            dtoList.add(typesDTO);
        }

        Long totalCount = pageObj.getTotalElements();
        return new PageImpl<TypesDTO>(dtoList, pageable, totalCount);
    }

    /*
        private RegionEntity getName(String name) {
            return typesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
        }
    */
    public List<TypesDTO> getAllByLang(Language lang) {
        List<TypesMapper> mapperList = typesRepository.findAllByLanguage(lang.name());
        List<TypesDTO> dtoList = new LinkedList<>();
        for (TypesMapper entity : mapperList) {
            TypesDTO dto = new TypesDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
