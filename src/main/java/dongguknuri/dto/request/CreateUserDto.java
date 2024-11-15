package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateUserDto(
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("department_id") Long departmentId,
        @JsonProperty("mbti") String mbti,
        @JsonProperty("personality") String personality
) implements Serializable {
    public static CreateUserDto of(
            final String name,
            final String email,
            final String password,
            final Long departmentId,
            final String mbti,
            final String personality
    ) {
        return CreateUserDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .departmentId(departmentId)
                .mbti(mbti)
                .personality(personality)
                .build();
    }
}
