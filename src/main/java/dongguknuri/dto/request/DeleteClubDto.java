package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record DeleteClubDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("club_id") Long clubId
) implements Serializable {
    public static DeleteClubDto of(
            final Long userId,
            final Long clubId
    ) {
        return DeleteClubDto.builder()
                .userId(userId)
                .clubId(clubId)
                .build();
    }
}
