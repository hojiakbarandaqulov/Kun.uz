package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.RegionEntity;
import dasturlash.uz.entity.TypesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
    Optional<Object> findByIdAndVisibleTrue(String id);

    void deleteById(String id);

    List<RegionEntity> findById(String id);


//    List<ArticleEntity> byId(Integer id);
    /*  @Query(value = "select a from article as a where id in" +
                     "(select article_id from atarticle_types where article_id=a.id and types_id = :id)order by a.created_date desc limit 5",nativeQuery = true)*
                     /

     */
    @Query(value = "select a from ArticleEntity as a " +
            "inner join a.articleTypes as ats " +
            "where ats.typesId= ?1 and a.visible = true and a.status = 'PUBLISHED' " +
            "order by a.createdDate desc " +
            " limit 5 ")
    List<ArticleEntity> getLast5ByTypesId(Integer typesId);

    @Query(value = "select a from ArticleEntity as a " +
            "inner join a.articleTypes as ats " +
            "where ats.typesId= ?1 and a.visible = true and a.status = 'PUBLISHED' " +
            "order by a.createdDate desc " +
            " limit 3 ")
    void getLast3ByTypesId(Integer typesId);

    @Query(value = "select a from ArticleEntity as a " +
            "inner join a.articleTypes as ats " +
            "where ats.typesId= ?1 and a.visible = true and a.status = 'NOT_PUBLISHED' " +
            "order by a.createdDate desc " +
            " limit 3 ")
    void getLast8ByTypesId(Integer typesId);

  /*  @Query("SELECT a.id, a.title, a.description, a.createdDate " +
            "FROM ArticleEntity a JOIN a.types at WHERE at.name = :typeName AND a.id <> :articleId " +
            "ORDER BY a.createdDate DESC")*/
    @Query(value = "select a.id, a.title, a.description, a.createdDate from ArticleEntity as a" +
            " inner join a.types at where at.id <> :typeId" +
            " order by a.createdDate desc ")
    List<Object[]> findLast4ByTypeAndExceptArticleId(@Param("typeId") Integer typeId);

    @Query(value = "select a from ArticleEntity as a " +
            "order by a.viewCount desc " +
            " limit 4")
    List<ArticleEntity> getRead4Articles();

    @Query("SELECT a FROM ArticleEntity a " +
            " JOIN a.tags t WHERE t.name = :tagName " +
            "ORDER BY a.createdDate DESC")
    List<ArticleEntity> findLast4ByTagName(@Param("tagName") String tagName);


    @Query("SELECT a FROM ArticleEntity a " +
            "WHERE a.types = :types AND a.regionId = :regionId" +
            " ORDER BY a.id DESC")
    void findLast5ByTypesAndRegionKey(@Param("types") TypesEntity types, @Param("regionId") Integer regionId);


    @Query("SELECT a FROM ArticleEntity a WHERE a.category.id = :categoryId")
    Page<ArticleEntity> findByCategoryId(@Param("categoryId") Integer categoryId);

//    List<ArticleEntity> findById(String id);
}