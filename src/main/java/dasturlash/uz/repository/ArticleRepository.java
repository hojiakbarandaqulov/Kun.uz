package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

}
