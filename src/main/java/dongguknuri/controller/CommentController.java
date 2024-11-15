package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateCommentDto;
import dongguknuri.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public ResponseDto<?> getCommentsByPost(@PathVariable Long postId) {
        return ResponseDto.ok(commentService.getCommentsByPost(postId));
    }

    @PostMapping
    public ResponseDto<?> createComment(@RequestBody CreateCommentDto createCommentDto) {
        return ResponseDto.ok(commentService.createComment(createCommentDto));
    }
}
