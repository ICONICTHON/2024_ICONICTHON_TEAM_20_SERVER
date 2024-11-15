package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateReplyDto;
import dongguknuri.service.ReplyService;
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
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/{commentId}")
    public ResponseDto<?> getRepliesByComment(@PathVariable Long commentId) {
        return ResponseDto.ok(replyService.getRepliesByComment(commentId));
    }

    @PostMapping
    public ResponseDto<?> createReply(@RequestBody CreateReplyDto createReplyDto) {
        return ResponseDto.created(replyService.createReply(createReplyDto));
    }

    @DeleteMapping("/{replyId}")
    public ResponseDto<?> deleteReply(@PathVariable Long replyId) {
        return ResponseDto.ok(replyService.deleteReply(replyId));
    }
}
