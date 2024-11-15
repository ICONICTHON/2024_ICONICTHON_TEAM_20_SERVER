package dongguknuri.service;

import dongguknuri.domain.Club;
import dongguknuri.domain.Department;
import dongguknuri.domain.Event;
import dongguknuri.dto.request.CreateEventDto;
import dongguknuri.dto.response.EventResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.ClubRepository;
import dongguknuri.repository.DepartmentRepository;
import dongguknuri.repository.EventRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public List<EventResponseDto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event -> EventResponseDto.of(
                        event.getEventId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getDate(),
                        event.getDepartment().getDepartmentId(),
                        event.getClub().getClubId()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventResponseDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EVENT));
        return EventResponseDto.builder()
                .eventId(event.getEventId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .departmentId(event.getDepartment().getDepartmentId())
                .clubId(event.getClub().getClubId())
                .build();
    }

    @Transactional
    public boolean createEvent(CreateEventDto createEventDto) {
        Department department = createEventDto.departmentId() != null
                ? departmentRepository.findById(createEventDto.departmentId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_DEPARTMENT)) : null;
        Club club = createEventDto.clubId() != null
                ? clubRepository.findById(createEventDto.clubId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_CLUB)) : null;

        eventRepository.save(Event.builder()
                .title(createEventDto.title())
                .description(createEventDto.description())
                .date(createEventDto.date())
                .department(department)
                .club(club)
                .build());

        return Boolean.TRUE;
    }

    @Transactional
    public boolean deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_EVENT));
        eventRepository.delete(event);
        return Boolean.TRUE;
    }
}
