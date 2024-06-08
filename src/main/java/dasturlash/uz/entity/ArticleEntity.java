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
   @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", columnDefinition = "text")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleStatus status=ArticleStatus.NOT_PUBLISHED;

    @Column(name = "shared_count")
    private Integer sharedCount = 0;

    @Column(name = "image_id",insertable = false, updatable = false)
    private Integer imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attach_id",insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "category_id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "moderator_id",insertable = false,updatable = false)
    private Integer moderatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator")
    private ProfileEntity moderator;

    @Column(name = "publisher_id", insertable = false, updatable = false)
    private Integer publisherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",insertable = false, updatable = false)
    private ProfileEntity publisher;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
<<<<<<< HEAD
//    @ElementCollection
//    private List<ArticleTypesEntity> articleType;

=======
<<<<<<< HEAD
//    @ElementCollection
    private List<ArticleTypesEntity> articleType;
=======
<<<<<<< HEAD
//    @ElementCollection
    private List<Integer> articleType;
=======
>>>>>>> 4d235e882bdc6797ede9322bd0f35f5138c42c49

   /* @Column(name = "view_count")
    private Integer viewCount;*/

<<<<<<< HEAD
=======
    @ElementCollection
    private List<String> articleType;

>>>>>>> 513d4ac3166946f1a193142457d5ea66b31a38a9
>>>>>>> 4d235e882bdc6797ede9322bd0f35f5138c42c49
>>>>>>> 2e61bf5e2689c94a813a385e9a9afc0767de9c34
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
}
