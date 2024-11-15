package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateClubDto;
import dongguknuri.service.ClubService;
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
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseDto<?> getAllClubs() {
        return ResponseDto.ok(clubService.getAllClubs());
    }

    @GetMapping("/{clubId}")
    public ResponseDto<?> getClubById(@PathVariable Long clubId) {
        return ResponseDto.ok(clubService.getClubById(clubId));
    }

    @PostMapping
    public ResponseDto<?> createClub(@RequestBody CreateClubDto createClubDto) {
        return ResponseDto.created(clubService.createClub(createClubDto));
    }

    @DeleteMapping("/{clubId}")
    public ResponseDto<?> deleteClub(@PathVariable Long clubId) {
        return ResponseDto.ok(clubService.deleteClub(clubId));
    }

}
