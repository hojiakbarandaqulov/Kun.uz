package dasturlash.uz.repository;

import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {
    @Query(value = "select * from profile where email = :email and visible = true",nativeQuery = true)
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status =?2 where id =?1")
    int updateStatus(Integer profileId, ProfileStatus status);

   /* @Query(value = "select * from profile where email = :email and visible = true",nativeQuery = true)
    Optional<ProfileEntity> findByEmailAndVisibleTrue(@Param("email") String email);
*/
   Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email, String password, Boolean visible);
    Optional<ProfileEntity> findByPhoneAndPasswordAndVisible(String phone, String password, Boolean visible);
    Optional<ProfileEntity> findByPhoneAndVisibleTrue(String phone);
}
