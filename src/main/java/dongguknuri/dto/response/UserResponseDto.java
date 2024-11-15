package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record UserResponseDto(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email
) implements Serializable {
    public static UserResponseDto of(
            final String name,
            final String email
    ) {
        return UserResponseDto.builder()
                .name(name)
                .email(email)
                .build();
    }
}
