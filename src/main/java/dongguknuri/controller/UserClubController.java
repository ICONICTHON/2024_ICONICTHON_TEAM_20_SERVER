package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.DeleteClubDto;
import dongguknuri.dto.request.JoinClubDto;
import dongguknuri.service.UserClubService;
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
@RequestMapping("/api/user-clubs")
public class UserClubController {

    private final UserClubService userClubService;

    @GetMapping("/clubs")
    public ResponseDto<?> getAllClubs() {
        return ResponseDto.ok(userClubService.getAllClubs());
    }

    @PostMapping("/join")
    public ResponseDto<?> joinClub(@RequestBody JoinClubDto joinClubDto) {
        return ResponseDto.created(userClubService.joinClub(joinClubDto));
    }

    @GetMapping("/clubs/{userId}")
    public ResponseDto<?> getUserClubs(@PathVariable Long userId) {
        return ResponseDto.ok(userClubService.getUserClubs(userId));
    }

    @DeleteMapping("/leave")
    public ResponseDto<?> leaveClub(@RequestBody DeleteClubDto deleteClubDto){
        return ResponseDto.ok(userClubService.leaveClub(deleteClubDto));
    }
}
