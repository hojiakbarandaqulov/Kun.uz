package dasturlash.uz.entity;

//import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.Array;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
 @Id
 @GeneratedValue(generator = "uuid-hibernate-generator")
 @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
 private String id;

 @Column(name = "title")
 private String title;

 @Column(name = "description")
 private String description;

 @Column(columnDefinition = "text")
 private String content;

 @Column(name = "shared_count")
 private Integer sharedCount;

 @Column(name = "view_count")
 private Integer viewCount;

 @Column(name = "image_id")
 private Integer imageId;

 @Column(name = "create_date")
 private LocalDateTime createDate = LocalDateTime.now();

 @Column(name = "published_date")
 private LocalDateTime publishedDate;

 @Column(name = "visible")
 private Boolean visible = Boolean.TRUE;

 @Column(name = "region_id")
 private Integer regionId;
 @ManyToOne
 @JoinColumn(name = "region_id", insertable = false, updatable = false)
 private RegionEntity region;

 @Column(name = "category_id")
 private Integer categoryId;
 @ManyToOne
 @JoinColumn(name = "category_id", insertable = false, updatable = false)
 private CategoryEntity category;

 @Column(name = "moderator_id")
 private Integer moderatorId;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
 private ProfileEntity moderator;

 @Column(name = "publisher_id")
 private Integer publisherId;
 @ManyToOne
 @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
 private ProfileEntity publisher;
   /* @OneToMany
    @JoinColumn(name = "articleTypes")
    private List<ArticleTypeEntity> articleType;*/

 @Enumerated(EnumType.STRING)
 private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;
}
