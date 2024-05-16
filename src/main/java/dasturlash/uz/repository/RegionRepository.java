package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity,Integer>{
    @Query(value = "select * from region ", nativeQuery = true)
    List<RegionEntity> findByLanguage(String language);
}
