package dasturlash.uz.repository;

import dasturlash.uz.entity.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity, Integer> {

    void deleteByArticleIdAndProfileId(String articleId, Integer profileId);

   /* @Query("SELECT new dasturlash.uz.dto.SavedArticleDTO " +
            "FROM SavedArticleEntity sa " +
            "JOIN ArticleEntity a ON sa.articleId = a.id " +
            "JOIN AttachEntity i ON a.id = i.id " +
            "WHERE sa.profileId = :profileId")*/
//    List<SavedArticleDTO> findSavedArticlesByProfileId(Long profileId);

    List<SavedArticleEntity> findByProfileId(Integer profileId);

//    void deleteByArticleId(String articleId);

    @Query("from SavedArticleEntity s where s.articleId = :articleId")
    Optional<SavedArticleEntity> findByArticleId(@Param("articleId")String articleId);
}

