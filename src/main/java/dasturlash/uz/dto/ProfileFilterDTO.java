package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.service.ProfileService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileFilterDTO {
   private String name;
   private String surname;
   private String phone;
   private ProfileRole role;
   private LocalDate createdDateFrom;
   private LocalDate createdDateTo;
}
