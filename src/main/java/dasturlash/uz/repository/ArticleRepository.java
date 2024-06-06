package dasturlash.uz.repository;

import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
    Optional<Object> findByIdAndVisibleTrue(String id);

    void deleteById(String id);

     ArticleEntity findById(String id);
   /*  @Query(value = "select a from article as a where id in" +
                    "(select article_id from atarticle_types where article_id=a.id and types_id = :id)order by a.created_date desc limit 5",nativeQuery = true)*
                    /

    */
    @Query(value = """
            select a from article as a
                     where a.id in (select article_id from article_types
                                                    where article_id = a.id and types_id = :id)
                       order by a.created_date desc
                       limit 5
            """, nativeQuery = true)
     List<ArticleEntity> findTop5By(@Param("id") Integer id);

//    List<ArticleRequestDTO> findTypeIdOrder(List<String> type);
//    List<ArticleRequestDTO> findTop5ByArticleTypeOrderByCreatedDateDesc(List<String> articleType);
}