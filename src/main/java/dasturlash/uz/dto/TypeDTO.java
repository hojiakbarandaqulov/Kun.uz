package dasturlash.uz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDTO {
    private UUID id;
    private String name;
}
