package dasturlash.uz.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class SmsTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token")
    private String token;

}
