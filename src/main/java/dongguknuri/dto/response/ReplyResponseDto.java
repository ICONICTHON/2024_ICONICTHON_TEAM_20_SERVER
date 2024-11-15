package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ReplyResponseDto(
        @JsonProperty("reply_id") Long id,
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("comment_id") Long commentId,
        @JsonProperty("created_at")LocalDateTime createdAt
        ) implements Serializable {
    public static ReplyResponseDto of(
            final Long id,
            final String content,
            final Long userId,
            final Long commentId,
            final LocalDateTime createdAt
    ) {
        return ReplyResponseDto.builder()
                .id(id)
                .content(content)
                .userId(userId)
                .commentId(commentId)
                .createdAt(createdAt)
                .build();
    }
}
