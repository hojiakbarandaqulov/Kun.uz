package dasturlash.uz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "type")

public class TypeEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;
}
