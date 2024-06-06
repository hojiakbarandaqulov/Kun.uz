package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleTypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleTypesRepository extends JpaRepository<ArticleTypesEntity, Integer> {

    List<Integer> findAllTypesIdByArticleId(String articleId);

    void deleteByArticleIdAndTypesId(String articleId, Integer oldId);
    void deleteById(Integer id);
}
