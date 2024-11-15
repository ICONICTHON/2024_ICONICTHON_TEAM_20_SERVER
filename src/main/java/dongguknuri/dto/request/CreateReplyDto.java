package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreateReplyDto(
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("comment_id") Long commentId
) implements Serializable {
    public static CreateReplyDto of(
            final String content,
            final Long userId,
            final Long commentId
    ) {
        return CreateReplyDto.builder()
                .content(content)
                .userId(userId)
                .commentId(commentId)
                .build();
    }
}
