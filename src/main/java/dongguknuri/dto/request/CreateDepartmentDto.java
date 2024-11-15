package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateDepartmentDto(
        @JsonProperty("name") String name
) implements Serializable {
    public static CreateDepartmentDto of(
            final String name
    ) {
        return CreateDepartmentDto.builder()
                .name(name)
                .build();
    }
}
