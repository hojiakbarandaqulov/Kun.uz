package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProfileDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;
    private LocalDate createdDate;
    private Integer photoId;
    private String jwt;
}
