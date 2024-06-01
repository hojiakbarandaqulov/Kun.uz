package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.mapper.RegionMapper;
import dasturlash.uz.mapper.TypesMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<RegionEntity, Integer> {

    @Query(value = "select * from region ", nativeQuery = true)
    List<RegionEntity> findByLanguage(Object language);

    @Query(value = "select id," +
            "  CASE :language " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'EN' THEN name_en " +
            "   WHEN 'RU' THEN name_ru " +
            "  END as name " +
            "from region order by order_number asc;", nativeQuery = true)
    List<RegionMapper> findAllByLanguage(@Param("language") String language);
}
