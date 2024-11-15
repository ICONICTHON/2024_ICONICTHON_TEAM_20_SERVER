package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record UserLoginDto(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password
) implements Serializable {
    public static UserLoginDto of(
            final String email,
            final String password
    ) {
        return UserLoginDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
