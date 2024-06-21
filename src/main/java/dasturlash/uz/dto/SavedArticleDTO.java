package dasturlash.uz.dto;

import dasturlash.uz.dto.article.ArticleDTO;
import dasturlash.uz.entity.ArticleEntity;
import dasturlash.uz.entity.ProfileEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SavedArticleDTO {
    private Long id;
    private String articleId;
    private Integer profileId;
    private String title;
    private String description;
    private Long imageId;
    private String imageUrl;


    public SavedArticleDTO(Long id, String articleId, String title, String description, Long imageId, String imageUrl) {
        this.id = id;
        this.articleId = articleId;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public SavedArticleDTO(Integer id, ArticleDTO articleDTO) {

    }
}
