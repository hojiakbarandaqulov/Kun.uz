package dasturlash.uz.service;

import dasturlash.uz.dto.*;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.repository.FilterResponseDTO;
import dasturlash.uz.repository.ProfileCustomRepository;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {
    //    private static final File UPLOAD_DIR = path/;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCustomRepository profileCustomRepository;
    public ProfileDTO create(ProfileCreateDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setEmail(profileDTO.getEmail());
        entity.setPhone(profileDTO.getPhone());
        entity.setPassword(profileDTO.getPassword());
        entity.setStatus(profileDTO.getStatus());
        entity.setRole(profileDTO.getRole());
        profileRepository.save(entity);
        return profileToDTO(entity);

    }

    public Boolean update(Integer id, ProfileCreateDTO dto) {
        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        profileRepository.save(entity);
        return true;
    }

    public ProfileDTO profileToDTO(ProfileEntity entity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setPhone(entity.getPhone());
        profileDTO.setPassword(entity.getPassword());
        profileDTO.setStatus(entity.getStatus());
        profileDTO.setRole(entity.getRole());
        return profileDTO;
    }

    private ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

    private ProfileEntity getUserUpdateRole(ProfileRole role) {
        if (role.equals(ProfileRole.ROLE_USER)) {
            return profileRepository.findByRole(role.name());
        }
        return null;
    }

    public PageImpl<ProfileDTO> getAllPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : pageObj.getContent()) {
            dtoList.add(profileToDTO(entity));
        }

        Long totalCount = pageObj.getTotalElements();
        return new PageImpl<ProfileDTO>(dtoList, pageable, totalCount);
    }

    public Boolean delete(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }

   /* public PageImpl<ProfileDTO> paginationWithName(ProfileFilterDTO filterDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        FilterResponseDTO<ProfileEntity> filterResponseDTO = profileCustomRepository.filter(filterDTO,page,size);
        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : filterResponseDTO.getContent()) {
            ProfileDTO dto = new ProfileDTO();
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setRole(entity.getRole());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }

        Long totalCount = filterResponseDTO.getTotalCount();

        return new PageImpl<ProfileDTO>(dtoList,pageable,totalCount);
    }*/
    public PageImpl<ProfileDTO> filter(ProfileFilterDTO filter, int page, int size) {
        FilterResponseDTO<ProfileEntity> filterResponse = profileCustomRepository.filter(filter, page, size);

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : filterResponse.getContent()) {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setCreatedDate(entity.getCreatedDate().toLocalDate());
            dtoList.add(dto);
        }
        return new PageImpl<ProfileDTO>( dtoList, PageRequest.of(page,size), filterResponse.getTotalCount());
    }


/*    public Boolean updateRole(ProfileRole role, ProfileCreateDTO dto) {
        ProfileEntity entity=profileRepository.findByRole(role.name());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        profileRepository.save(entity);
        return true;
    }*/

}

