package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PostResponseDto(
        @JsonProperty("post_id") Long postId,
        @JsonProperty("title") String title,
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("board_id") Long boardId,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("updated_at") LocalDateTime updatedAt
) implements Serializable {
    public static PostResponseDto of(
            final Long postId,
            final String title,
            final String content,
            final Long userId,
            final Long boardId,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return PostResponseDto.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .userId(userId)
                .boardId(boardId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
