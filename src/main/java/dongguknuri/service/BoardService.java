package dongguknuri.service;

import dongguknuri.domain.board.Board;
import dongguknuri.domain.course.Course;
import dongguknuri.dto.request.CreateBoardDto;
import dongguknuri.dto.response.BoardResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.board.BoardRepository;
import dongguknuri.repository.course.CourseRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(board -> BoardResponseDto.of(
                        board.getBoardId(),
                        board.getName(),
                        board.getDescription(),
                        board.getCourse().getCourseId()
                )).collect(Collectors.toList());
    }

    @Transactional
    public boolean createBoard(CreateBoardDto createBoardDto) {
        Course course = courseRepository.findById(createBoardDto.courseId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COURSE));
        boardRepository.save(Board.builder()
                .name(createBoardDto.name())
                .description(createBoardDto.description())
                .course(course)
                .build());

        return Boolean.TRUE;
    }
}
