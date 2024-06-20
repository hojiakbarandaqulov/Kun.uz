package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleLikeEntity;
import dasturlash.uz.entity.CommentEntity;
import dasturlash.uz.entity.CommentLikeEntity;
import dasturlash.uz.enums.EmotionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Integer> {

    Optional<CommentLikeEntity> findByCommentIdAndProfileId(String commentId, Integer profileId);

    @Modifying
    @Transactional
    @Query("update CommentLikeEntity  set status =:status where commentId=:commentId and profileId=:profileId")
    int update(@Param("status") EmotionStatus status,
               @Param("commentId") String commentId,
               @Param("profileId") Integer profileId);


    @Modifying
    @Transactional
    @Query("delete from CommentLikeEntity where commentId=:commentId and profileId=:profileId")
    int delete(@Param("commentId") String commentId,
               @Param("profileId") Integer profileId);

}
