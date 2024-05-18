package dasturlash.uz.service;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.ProfileDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO create(ProfileDTO profileDTO) {
        ProfileEntity entity=new ProfileEntity();

        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setEmail(profileDTO.getEmail());
        entity.setPhone(profileDTO.getPhone());
        entity.setPassword(profileDTO.getPassword());
        entity.setStatus(profileDTO.getStatus());
        entity.setRole(profileDTO.getRole());
        profileRepository.save(entity);
        profileDTO.setId(entity.getId());
        return profileDTO;
    }

    public Boolean update(Integer id, ProfileDTO dto) {
        ProfileEntity entity=get(id);

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
    private ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Profile not found"));
    }

}
