package dasturlash.uz.repository;

import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.enums.Language;
import dasturlash.uz.mapper.CategoryMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

    @Query(value = "select id," +
            "case :language" +
            "   when 'UZ' then name_uz" +
            "   when 'RU' then name_ru" +
            "   when 'EN' then name_en" +
            " end as name" +
            " from category order by  order_number desc;", nativeQuery = true)
    List<CategoryMapper> findByLanguage(@Param("language") String language);
}
