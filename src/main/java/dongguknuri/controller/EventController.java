package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateEventDto;
import dongguknuri.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseDto<?> getAllEvents() {
        return ResponseDto.ok(eventService.getAllEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseDto<?> getEventById(@PathVariable Long eventId) {
        return ResponseDto.ok(eventService.getEventById(eventId));
    }

    @PostMapping("/create")
    public ResponseDto<?> createEvent(@RequestBody CreateEventDto createEventDto){
        return ResponseDto.created(eventService.createEvent(createEventDto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseDto<?> deleteEvent(@PathVariable Long eventId) {
        return ResponseDto.ok(eventService.deleteEvent(eventId));
    }
}
