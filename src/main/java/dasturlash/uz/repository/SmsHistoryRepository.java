package dasturlash.uz.repository;

import dasturlash.uz.entity.history.SmsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SmsHistoryRepository extends JpaRepository<SmsHistoryEntity, Integer> {

    Long countByPhoneAndCreatedDateBetween(String phone, LocalDateTime from, LocalDateTime to);
    // select count(*) from email_history createdDate between :from and :to

    Optional<SmsHistoryEntity> findByPhone(String phone);

    Optional<SmsHistoryEntity> findByCreatedDate(LocalDateTime createdDate);

}
