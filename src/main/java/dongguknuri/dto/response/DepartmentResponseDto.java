package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record DepartmentResponseDto(
        @JsonProperty("department_id") Long departmentId,
        @JsonProperty("name") String name
) implements Serializable {
    public static DepartmentResponseDto of(
            final Long departmentId,
            final String name
    ) {
        return DepartmentResponseDto.builder()
                .departmentId(departmentId)
                .name(name)
                .build();
    }
}
