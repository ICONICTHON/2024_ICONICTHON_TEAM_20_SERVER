package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreatePointDto;
import dongguknuri.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointHistoryController {

    private final PointHistoryService pointHistoryService;

    @GetMapping("/{userId}")
    public ResponseDto<?> getUserPointHistory(@PathVariable Long userId) {
        return ResponseDto.ok(pointHistoryService.getUserPointHistory(userId));
    }

    @PostMapping
    public ResponseDto<?> addPoint(@RequestBody CreatePointDto createPointDto) {
        return ResponseDto.created(pointHistoryService.addPoint(createPointDto));
    }
}
