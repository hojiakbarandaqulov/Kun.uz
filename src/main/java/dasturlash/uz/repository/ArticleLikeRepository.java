package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleLikeEntity;
import dasturlash.uz.enums.EmotionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLikeEntity, Integer> {
    /* @Query(value = "select a from ArticleLikeEntity a " +
             "where a.id=:articleId and a.profileId=:profileId")*/
    Optional<ArticleLikeEntity> findByArticleIdAndProfileId(String articleId, Integer profileId);


    @Modifying
    @Transactional
    @Query("update ArticleLikeEntity a set a.status =:status where a.articleId=:articleId and a.profileId=:profileId")
    int update(@Param("status") EmotionStatus status,
               @Param("articleId") String articleId,
               @Param("profileId") Integer profileId);

    @Modifying
    @Transactional
    @Query("delete from ArticleLikeEntity a where a.articleId=:articleId and a.profileId=:profileId")
    int delete(@Param("articleId") String articleId,
               @Param("profileId") Integer profileId);

    @Query("select count(a) from  ArticleLikeEntity a where a.articleId =?1 and a.status ='LIKE' ")
    Long getArticleLikeCount(String articleId);

}
