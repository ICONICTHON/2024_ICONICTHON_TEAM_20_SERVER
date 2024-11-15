package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreatePointDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("points") int points
) implements Serializable {
    public static CreatePointDto of(
            final Long userId,
            final int points
    ) {
        return CreatePointDto.builder()
                .userId(userId)
                .points(points)
                .build();
    }
}
