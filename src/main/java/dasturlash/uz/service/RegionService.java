package dasturlash.uz.service;

import dasturlash.uz.dto.RegionCreateDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.RegionMapper;
import dasturlash.uz.repository.RegionRepository;
import org.jetbrains.annotations.NotNull;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionCreateDTO dto) {
        RegionEntity entity = new RegionEntity();

        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return toDTO(entity);
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean update(Integer id, RegionCreateDTO dto) {
        RegionEntity entity = get(id);
//        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return true;
    }

    private RegionEntity get(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new AppBadException("Region not found"));
    }

    public Boolean delete(Integer id) {
        regionRepository.deleteById(id);
        return true;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            dtoList.add(toDTO(entity));
        }
        return dtoList;
    }
  /*  public List<RegionDTO> getByLanguage(Language language) {
        Iterable<RegionEntity> entity = regionRepository.findAllVisible();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity region : entity) {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(region.getId());
            switch (language) {
                case uz -> regionDTO.setNameUz(region.getNameUz());
                case ru -> regionDTO.setNameRu(region.getNameRu());
                case en -> regionDTO.setNameEn(region.getNameEn());
            }
            dtoList.add(regionDTO);
        }
        return dtoList;
    }*/
    public List<RegionDTO> getAllByLanguage(Language lang) {
        List<RegionMapper> mapperList = regionRepository.findAll(lang.name());
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionMapper entity : mapperList) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
