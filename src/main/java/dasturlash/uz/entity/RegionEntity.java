package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_number")
    private Integer order_number;
    @Column(name = "name_uz")
    private String name_uz;
    @Column(name = "name_ru")
    private String name_ru;
    @Column(name = "name_en")
    private String name_en;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDate created_date;
}
