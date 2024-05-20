package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity,Integer>{
    @Query(value = "select * from region ", nativeQuery = true)
    List<RegionEntity> findByLanguage(Object language);
 /*   @Query(value = "select * from region where name_uz=:language", nativeQuery = true)
    List<RegionEntity> findByName_Uz(String language);
    @Query(value = "select * from region where name_ru=:language", nativeQuery = true)
    List<RegionEntity> findByName_Ru(String language);

    @Query(value = "select * from region where name_en=:language", nativeQuery = true)
    List<RegionEntity> findByName_En(String language);*/
}
