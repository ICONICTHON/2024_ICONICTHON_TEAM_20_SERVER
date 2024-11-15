package dongguknuri.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public record DeleteLikeDto(
        @JsonProperty("user_id") Long userId,
        @JsonProperty("post_id") Long postId
) implements Serializable {
    public static DeleteLikeDto of(
            final Long userId,
            final Long postId
    ) {
        return DeleteLikeDto.builder()
                .userId(userId)
                .postId(postId)
                .build();
    }
}