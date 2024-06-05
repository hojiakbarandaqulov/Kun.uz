package dasturlash.uz.repository;

import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.entity.history.SmsHistoryEntity;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{

    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity set status =?2 where id =?1")
    void updateStatus(Integer profileId, ProfileStatus status);

    /* @Query(value = "select * from profile where email = :email and visible = true",nativeQuery = true)
     Optional<ProfileEntity> findByEmailAndVisibleTrue(@Param("email") String email);*/
//   Optional<ProfileEntity> findByEmailAndPasswordAndVisible(String email);
    Optional<ProfileEntity> findByPhoneAndPasswordAndVisibleIsTrue(String phone, String password);

    Optional<ProfileEntity> findByPhoneAndVisibleTrue(String phone);

//    Optional<SmsHistoryEntity> findByPhone(String phone);
}
