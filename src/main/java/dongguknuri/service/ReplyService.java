package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.board.Comment;
import dongguknuri.domain.board.Reply;
import dongguknuri.dto.request.CreateReplyDto;
import dongguknuri.dto.response.ReplyResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.board.CommentRepository;
import dongguknuri.repository.board.ReplyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public List<ReplyResponseDto> getRepliesByComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMENT));

        return replyRepository.findAllByComment(comment).stream()
                .map(reply -> ReplyResponseDto.of(
                        reply.getReplyId(),
                        reply.getContent(),
                        reply.getUser().getUserId(),
                        reply.getComment().getCommentId(),
                        reply.getCreatedAt()
                )).collect(Collectors.toList());
    }

    @Transactional
    public boolean createReply(CreateReplyDto createReplyDto) {
        User user = userRepository.findById(createReplyDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Comment comment = commentRepository.findById(createReplyDto.commentId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_COMMENT));

        replyRepository.save(Reply.builder()
                .content(createReplyDto.content())
                .user(user)
                .comment(comment)
                .build());

        return Boolean.TRUE;
    }

    @Transactional
    public boolean deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_REPLY));

        replyRepository.delete(reply);
        return Boolean.TRUE;
    }
}
