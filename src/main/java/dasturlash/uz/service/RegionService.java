package dasturlash.uz.service;

import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enumLanguage.Language;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    private String nameUz = String.valueOf(Language.name_uz);
    private String nameRu = String.valueOf(Language.name_ru);
    private String nameEn = String.valueOf(Language.name_en);

    public RegionDTO create(RegionDTO regionDTO) {
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
    }

    public Boolean update(Integer id, RegionDTO dto) {
        RegionEntity entity = get(id);
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        regionRepository.save(entity);
        return true;
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
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(region.getId());
            regionDTO.setNameUz(region.getNameUz());
            regionDTO.setNameRu(region.getNameRu());
            regionDTO.setNameEn(region.getNameEn());
            regionDTO.setVisible(regionDTO.getVisible());
            regionDTO.setCreatedDate(region.getCreatedDate());
            dtoList.add(regionDTO);
        }
        return dtoList;
    }

    /*
        private RegionEntity getName(String name) {
            return regionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Region not found"));
        }
    */
    public List<RegionDTO> getByLanguage(String language) {
        Iterable<RegionEntity> entity = regionRepository.findByLanguage(language);
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity region : entity) {
            RegionDTO regionDTO = new RegionDTO();
           /* switch (language) {
                case nameUz:
                    regionDTO.setId(region.getId());
                    regionDTO.setNameUz();(region.getNameUz()());
                    dtoList.add(regionDTO);
                    break;
                case nameRu:
                    regionDTO.setId(region.getId());
                    regionDTO.setNameRu();(region.getNameRu()());
                    dtoList.add(regionDTO);
                    break;
                case nameEn:
                    regionDTO.setId(region.getId());
                    regionDTO.setNameEn();(region.getNameEn()());
                    dtoList.add(regionDTO);
                    break;
                default:
                    return null;
            }*/
            if (language.equals(nameUz)){
                regionDTO.setId(region.getId());
                regionDTO.setNameUz(region.getNameUz());
                dtoList.add(regionDTO);
            } else if (language.equals(nameRu)) {
                regionDTO.setId(region.getId());
                regionDTO.setNameRu(region.getNameRu());
                dtoList.add(regionDTO);
            } else if (language.equals(nameEn)) {
                regionDTO.setId(region.getId());
                regionDTO.setNameEn(region.getNameEn());
                dtoList.add(regionDTO);
            }
        }
        return dtoList;
    }
}
