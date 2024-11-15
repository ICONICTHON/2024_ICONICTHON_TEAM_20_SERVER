package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateBoardDto(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("course_id") Long courseId
) implements Serializable {
    public static CreateBoardDto of(
            final String name,
            final String description,
            final Long courseId
    ) {
        return CreateBoardDto.builder()
                .name(name)
                .description(description)
                .courseId(courseId)
                .build();
    }
}
