package dasturlash.uz.repository;

import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.entity.TypesEntity;
import dasturlash.uz.mapper.RegionMapper;
import dasturlash.uz.mapper.TypesMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface TypesRepository extends JpaRepository<TypesEntity, Integer> {
   /* @Query(value = "select * from types ", nativeQuery = true)
    List<TypesEntity> findAll(String language);*/
    @Query(value = " select id, " +
            " CASE :language " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'EN' THEN name_en " +
            "   WHEN 'RU' THEN name_ru " +
            "  END as name " +
            "from types order by order_number asc ; ", nativeQuery = true)
    List<TypesMapper> findAllByLanguage(@Param("language") String language);
}
