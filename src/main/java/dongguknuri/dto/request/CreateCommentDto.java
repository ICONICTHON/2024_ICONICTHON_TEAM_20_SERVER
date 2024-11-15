package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateCommentDto(
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("post_id") Long postId
) implements Serializable {
    public static CreateCommentDto of(
            final String content,
            final Long userId,
            final Long postId
    ) {
        return CreateCommentDto.builder()
                .content(content)
                .userId(userId)
                .postId(postId)
                .build();
    }
}
