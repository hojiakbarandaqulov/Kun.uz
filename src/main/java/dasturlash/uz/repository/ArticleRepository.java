package dasturlash.uz.repository;

import dasturlash.uz.dto.article.ArticleRequestDTO;
import dasturlash.uz.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {
//    List<ArticleRequestDTO> findTypeIdOrder(List<String> type);
    //    List<ArticleRequestDTO> findTop5ByArticleTypeOrderByCreatedDateDesc(List<String> articleType);
}