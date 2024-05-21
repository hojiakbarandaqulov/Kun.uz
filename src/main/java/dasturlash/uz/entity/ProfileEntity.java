package dasturlash.uz.entity;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter

@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ProfileRole role;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "photo_id")
    private Integer photoId;

}
