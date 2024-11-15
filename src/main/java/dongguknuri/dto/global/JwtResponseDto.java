package dongguknuri.dto.global;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record JwtResponseDto(
        @JsonProperty("jwt") String jwt,
        @JsonProperty("name") String name
) implements Serializable {
    public static JwtResponseDto of(
            final String jwt,
            final String name
    ) {
        return JwtResponseDto.builder()
                .jwt(jwt)
                .name(name)
                .build();
    }
}