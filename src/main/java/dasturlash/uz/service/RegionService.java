package dasturlash.uz.service;

import dasturlash.uz.dto.create.RegionCreateDTO;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.mapper.RegionMapper;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        return regionToDTO(entity);
    }

    public Boolean update(Integer id, RegionCreateDTO dto) {
        RegionEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return true;
    }

    public RegionDTO regionToDTO(RegionEntity entity) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(entity.getId());
        regionDTO.setOrderNumber(entity.getOrderNumber());
        regionDTO.setNameUz(entity.getNameUz());
        regionDTO.setNameRu(entity.getNameRu());
        regionDTO.setNameEn(entity.getNameEn());
        regionDTO.setVisible(entity.getVisible());
        regionDTO.setCreatedDate(entity.getCreatedDate());
        return regionDTO;
    }

    private RegionEntity get(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
    }

    public Boolean delete(Integer id) {
        RegionEntity entity = get(id);
        regionRepository.delete(entity);
        return true;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> entity = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity region : entity) {
            dtoList.add(regionToDTO(region));
        }
        return dtoList;
    }

    public List<RegionDTO> getAllByLang(Language lang) {
        List<RegionMapper> mapperList = regionRepository.findAllByLanguage(lang.name());
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionMapper entity : mapperList) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public RegionEntity getId(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Region not found: " + id);
        }
        return optional.get();
    }
}