package dasturlash.uz.service;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.dto.TypesDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.entity.TypesEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.enums.Language;
import dasturlash.uz.repository.RegionRepository;
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

    private String nameUz = String.valueOf(Language.nameUz);
    private String nameRu = String.valueOf(Language.nameRu);
    private String nameEn = String.valueOf(Language.nameEn);

    public TypesDTO create(TypesDTO typesDTO) {
        TypesEntity typesEntity = new TypesEntity();

        typesEntity.setOrderNumber(typesDTO.getOrderNumber());
        typesEntity.setNameUz(typesDTO.getNameUz());
        typesEntity.setNameRu(typesDTO.getNameRu());
        typesEntity.setNameEn(typesDTO.getNameEn());
        typesRepository.save(typesEntity);
        typesDTO.setId(typesEntity.getId());
        return typesDTO;
    }

    public Boolean update(Integer id, TypesDTO dto) {
        TypesEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typesRepository.save(entity);
        return true;
    }

    private TypesEntity get(Integer id) {
        return typesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    public Boolean delete(Integer id) {
        TypesEntity entity = get(id);
        typesRepository.delete(entity);
        return true;
    }

    public PageImpl<TypesDTO> getAllPagination(int page, int size) {
        Sort sort=Sort.by(Sort.Order.desc("createdDate"));
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
    public List<TypesDTO> getByLanguage(String language) {
        Iterable<TypesEntity> entity = typesRepository.findAll(language);
        List<TypesDTO> dtoList = new LinkedList<>();
        for (TypesEntity types : entity) {
            TypesDTO typesDTO = new TypesDTO();
            if (language.equals(nameUz)) {
                typesDTO.setId(types.getId());
                typesDTO.setNameUz(types.getNameUz());
                dtoList.add(typesDTO);
            } else if (language.equals(nameRu)) {
                typesDTO.setId(types.getId());
                typesDTO.setNameRu(types.getNameRu());
                dtoList.add(typesDTO);
            } else if (language.equals(nameEn)) {
                typesDTO.setId(types.getId());
                typesDTO.setNameEn(types.getNameEn());
                dtoList.add(typesDTO);
            }
        }
        return dtoList;
    }
}
