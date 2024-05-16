package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.entity.TypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface TypesRepository extends JpaRepository<TypesEntity, Integer> {
    @Query(value = "select * from types ", nativeQuery = true)
    List<TypesEntity> findAll(String language);
}
