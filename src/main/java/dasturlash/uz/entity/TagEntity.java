package dasturlash.uz.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "article_tag")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<ArticleEntity> articles;
}
