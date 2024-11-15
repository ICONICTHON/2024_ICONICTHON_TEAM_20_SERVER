package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateUserCourseDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("course_id") Long courseId
) implements Serializable {
    public static CreateUserCourseDto of(
            final Long userId,
            final Long courseId
    ) {
        return CreateUserCourseDto.builder()
                .userId(userId)
                .courseId(courseId)
                .build();
    }
}
