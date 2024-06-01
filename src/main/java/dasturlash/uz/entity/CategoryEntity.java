package dasturlash.uz.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

@Entity
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
