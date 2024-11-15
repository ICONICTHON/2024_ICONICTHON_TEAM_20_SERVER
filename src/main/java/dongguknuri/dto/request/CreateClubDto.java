package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateClubDto(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description
) implements Serializable {
    public static CreateClubDto of(
            final String name,
            final String description
    ) {
        return CreateClubDto.builder()
                .name(name)
                .description(description)
                .build();
    }
}
