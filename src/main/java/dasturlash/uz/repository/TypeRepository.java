package dasturlash.uz.repository;

import dasturlash.uz.entity.TypeEntity;
import dasturlash.uz.entity.TypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TypeRepository extends JpaRepository<TypeEntity, UUID> {

}
