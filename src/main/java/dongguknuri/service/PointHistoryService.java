package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.board.PointHistory;
import dongguknuri.dto.request.CreatePointDto;
import dongguknuri.dto.response.PointHistoryResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.board.PointHistoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PointHistoryResponseDto> getUserPointHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return pointHistoryRepository.findByUser(user).stream()
                .map(p -> PointHistoryResponseDto.of(
                        p.getHistoryId(),
                        p.getUser().getUserId(),
                        p.getPoints()
                )).collect(Collectors.toList());
    }

    @Transactional
    public boolean addPoint(CreatePointDto createPointDto) {
        User user = userRepository.findById(createPointDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        pointHistoryRepository.save(PointHistory.builder()
                .user(user)
                .points(createPointDto.points())
                .build());

        return Boolean.TRUE;
    }
}
