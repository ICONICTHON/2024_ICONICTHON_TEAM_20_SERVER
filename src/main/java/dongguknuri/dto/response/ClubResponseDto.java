package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record ClubResponseDto(
        @JsonProperty("club_id") Long clubId,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description
) implements Serializable {
    public static ClubResponseDto of(
            final Long clubId,
            final String name,
            final String description
    ) {
        return ClubResponseDto.builder()
                .clubId(clubId)
                .name(name)
                .description(description)
                .build();
    }
}
