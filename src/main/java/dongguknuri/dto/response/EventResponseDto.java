package dongguknuri.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record EventResponseDto(
        @JsonProperty("event_id") Long eventId,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("date")LocalDate date,
        @JsonProperty("department_id") Long departmentId,
        @JsonProperty("club_id") Long clubId
        ) implements Serializable {
    public static EventResponseDto of(
            final Long eventId,
            final String title,
            final String description,
            final LocalDate date,
            final Long departmentId,
            final Long clubId
    ) {
        return EventResponseDto.builder()
                .eventId(eventId)
                .title(title)
                .description(description)
                .date(date)
                .departmentId(departmentId)
                .clubId(clubId)
                .build();
    }
}
