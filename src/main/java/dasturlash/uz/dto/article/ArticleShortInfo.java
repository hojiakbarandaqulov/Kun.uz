package dasturlash.uz.dto.article;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ArticleShortInfo {
    private Long id;
    private String title;
    private String summary;
    private LocalDateTime createdDate;
    private Integer viewCount;

    public ArticleShortInfo(Long id, String title, String summary, LocalDateTime createdDate, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.createdDate = createdDate;
        this.viewCount = viewCount;
    }
}
