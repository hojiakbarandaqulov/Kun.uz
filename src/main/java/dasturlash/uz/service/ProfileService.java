package dasturlash.uz.service;

import dasturlash.uz.controller.AuthController;
import dasturlash.uz.dto.*;
import dasturlash.uz.dto.create.ProfileCreateDTO;
import dasturlash.uz.dto.filter.ProfileFilterDTO;
import dasturlash.uz.dto.response.FilterResponseDTO;
import dasturlash.uz.dto.update.ProfileUpdateDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.customRepository.ProfileCustomRepository;
import dasturlash.uz.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Slf4j
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final ProfileCustomRepository profileCustomRepository;


    public ProfileService(ProfileRepository profileRepository, ProfileCustomRepository profileCustomRepository) {
        this.profileRepository = profileRepository;
        this.profileCustomRepository = profileCustomRepository;
    }

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

    public Boolean update(Integer id,ProfileCreateDTO profile) {
        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profile.getName());
        profileEntity.setSurname(profile.getSurname());
        profileEntity.setEmail(profile.getEmail());
        profileEntity.setPhone(profile.getPhone());
        profileEntity.setPassword(profile.getPassword());
        profileEntity.setStatus(profile.getStatus());
        profileEntity.setRole(profile.getRole());
        profileRepository.save(profileEntity);
        return true;
    }


    public Boolean updateUser(Integer id, ProfileUpdateDTO profileUser) {
        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profileUser.getName());
        profileEntity.setSurname(profileUser.getSurname());
        profileRepository.save(profileEntity);
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

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            log.error("Profile not found id = {}", id);
            throw new AppBadException("Profile not found");
        });
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

    public Boolean deleteId(Integer id) {
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
            dto.setCreatedDate(entity.getCreatedDate());
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

