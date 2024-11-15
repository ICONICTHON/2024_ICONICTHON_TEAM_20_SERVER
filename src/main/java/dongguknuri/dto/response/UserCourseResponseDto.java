package dongguknuri.dto.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record UserCourseResponseDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("course_id") Long courseId
) implements Serializable {
    public static UserCourseResponseDto of(
            final Long userId,
            final Long courseId
    ) {
        return UserCourseResponseDto.builder()
                .userId(userId)
                .courseId(courseId)
                .build();
    }
}
