package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record PointHistoryResponseDto(
        @JsonProperty("history_id") Long id,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("points") int points
) implements Serializable {
    public static PointHistoryResponseDto of(
            final Long id,
            final Long userId,
            final int points
    ) {
        return PointHistoryResponseDto.builder()
                .id(id)
                .userId(userId)
                .points(points)
                .build();
    }
}
