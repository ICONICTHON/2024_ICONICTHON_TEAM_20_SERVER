package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record JoinClubDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("club_id") Long clubId
) implements Serializable {
    public static JoinClubDto of(
            final Long userId,
            final Long clubId
    ) {
        return JoinClubDto.builder()
                .userId(userId)
                .clubId(clubId)
                .build();
    }
}
