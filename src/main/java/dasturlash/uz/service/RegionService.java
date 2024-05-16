package dasturlash.uz.service;

import dasturlash.uz.RegionLanguage;
import dasturlash.uz.dto.RegionDTO;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    private String nameUz = String.valueOf(RegionLanguage.name_uz);
    private String nameRu = String.valueOf(RegionLanguage.name_ru);
    private String nameEn = String.valueOf(RegionLanguage.name_en);

    public RegionDTO create(RegionDTO regionDTO) {
        RegionEntity regionEntity = new RegionEntity();

        regionEntity.setOrder_number(regionDTO.getOrder_number());
        regionEntity.setName_uz(regionDTO.getName_uz());
        regionEntity.setName_ru(regionDTO.getName_ru());
        regionEntity.setName_en(regionDTO.getName_en());
        regionEntity.setVisible(regionDTO.getVisible());
        regionEntity.setCreated_date(regionDTO.getCreated_date());
        regionRepository.save(regionEntity);
        regionDTO.setId(regionEntity.getId());
        return regionDTO;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        RegionEntity entity = get(id);
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
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
            regionDTO.setName_uz(region.getName_uz());
            regionDTO.setName_ru(region.getName_ru());
            regionDTO.setName_en(region.getName_en());
            regionDTO.setVisible(regionDTO.getVisible());
            regionDTO.setCreated_date(region.getCreated_date());
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
                    regionDTO.setName_uz(region.getName_uz());
                    dtoList.add(regionDTO);
                    break;
                case nameRu:
                    regionDTO.setId(region.getId());
                    regionDTO.setName_ru(region.getName_ru());
                    dtoList.add(regionDTO);
                    break;
                case nameEn:
                    regionDTO.setId(region.getId());
                    regionDTO.setName_en(region.getName_en());
                    dtoList.add(regionDTO);
                    break;
                default:
                    return null;
            }*/
            if (language.equals(nameUz)){
                regionDTO.setId(region.getId());
                regionDTO.setName_uz(region.getName_uz());
                dtoList.add(regionDTO);
            } else if (language.equals(nameRu)) {
                regionDTO.setId(region.getId());
                regionDTO.setName_ru(region.getName_ru());
                dtoList.add(regionDTO);
            } else if (language.equals(nameEn)) {
                regionDTO.setId(region.getId());
                regionDTO.setName_en(region.getName_en());
                dtoList.add(regionDTO);
            }
        }
        return dtoList;
    }
}
