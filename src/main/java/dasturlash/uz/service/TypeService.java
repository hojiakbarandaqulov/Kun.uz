package dasturlash.uz.service;

import dasturlash.uz.dto.TypeDTO;
import dasturlash.uz.entity.TypeEntity;
import dasturlash.uz.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;
/*
    public TypeDTO createType(TypeDTO type) {
        typeRepository.save(type);
        return ;
    }

    public List<TypeDTO> getAllTypes() {
        return typeRepository.findAll();
    }

    public TypeEntity getTypeById(UUID id) {
        return typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
    }

    public Type updateType(UUID id, Type typeDetails) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new RuntimeException("Type not found"));
        type.setName(typeDetails.getName());
        return typeRepository.save(type);
    }

    public void deleteType(UUID id) {
        typeRepository.deleteById(id);
    }*/
}
