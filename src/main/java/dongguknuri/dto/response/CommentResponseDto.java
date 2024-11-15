package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CommentResponseDto(
        @JsonProperty("comment_id") Long id,
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("post_id") Long postId,
        @JsonProperty("created_at")LocalDateTime createdAt
        ) implements Serializable {
    public static CommentResponseDto of(
            final Long id,
            final String content,
            final Long userId,
            final Long postId,
            final LocalDateTime createdAt
    ) {
        return CommentResponseDto.builder()
                .id(id)
                .content(content)
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();
    }
}
