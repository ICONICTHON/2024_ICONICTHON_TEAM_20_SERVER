package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record CreatePostDto(
        @JsonProperty("title") String title,
        @JsonProperty("content") String content,
        @JsonProperty("user_id") Long userId,
        @JsonProperty("board_id") Long boardId
) implements Serializable {
    public static CreatePostDto of(
            final String title,
            final String content,
            final Long userId,
            final Long boardId
    ) {
        return CreatePostDto.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .boardId(boardId)
                .build();
    }
}
