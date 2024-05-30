package dasturlash.uz.entity;

//import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter

@Entity
public class ArticleEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    @Column(columnDefinition = "text")
    private String content;
    private int sharedCount;
    private Integer imageId;
    private Integer regionId;
//    private CategoryDTO category;
    private Integer moderatorId;
    private Integer publisherId;
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private boolean visible;
    private int viewCount;
    @ManyToMany
    @JoinTable(
            name = "article_type",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<TypeEntity> types;
}
