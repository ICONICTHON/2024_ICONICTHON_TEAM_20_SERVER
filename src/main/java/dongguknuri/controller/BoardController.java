package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateBoardDto;
import dongguknuri.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseDto<?> getAllBoards() {
        return ResponseDto.ok(boardService.getAllBoards());
    }

    @PostMapping
    public ResponseDto<?> createBoard(@RequestBody CreateBoardDto createBoardDto) {
        return ResponseDto.created(boardService.createBoard(createBoardDto));
    }
}
