package dongguknuri.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record CreateEventDto(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("date")LocalDate date,
        @JsonProperty("department_id") Long departmentId,
        @JsonProperty("club_id") Long clubId
        ) implements Serializable {
    public static CreateEventDto of(
            final String title,
            final String description,
            final LocalDate date,
            final Long departmentId,
            final Long clubId
    ) {
        return CreateEventDto.builder()
                .title(title)
                .description(description)
                .date(date)
                .departmentId(departmentId)
                .clubId(clubId)
                .build();
    }
}
