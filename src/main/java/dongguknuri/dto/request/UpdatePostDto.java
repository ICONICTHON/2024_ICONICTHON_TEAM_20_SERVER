package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record UpdatePostDto(
        @JsonProperty("post_id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("content") String content
) implements Serializable {
    public static UpdatePostDto of(
            final Long id,
            final String title,
            final String content
    ) {
        return UpdatePostDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
