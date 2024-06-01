package dasturlash.uz.repository;

import dasturlash.uz.entity.history.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {

    Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

    // select count(*) from email_history createdDate between :from and :to
    Optional<EmailHistoryEntity> findByEmail(String email);

    Optional<EmailHistoryEntity> findByCreatedDate(LocalDateTime createdDate);

    Page<EmailHistoryEntity> findAll(Pageable pageable);
}
