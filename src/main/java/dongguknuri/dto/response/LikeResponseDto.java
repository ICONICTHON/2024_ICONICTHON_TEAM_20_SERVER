package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record LikeResponseDto(
        @JsonProperty("like_id") Long id,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("post_id") Long postId
) implements Serializable {
    public static LikeResponseDto of(
            final Long id,
            final Long userId,
            final Long postId
    ) {
        return LikeResponseDto.builder()
                .id(id)
                .userId(userId)
                .postId(postId)
                .build();
    }
}
