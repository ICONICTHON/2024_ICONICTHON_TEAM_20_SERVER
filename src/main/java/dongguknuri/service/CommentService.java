package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.board.Comment;
import dongguknuri.domain.board.Post;
import dongguknuri.dto.request.CreateCommentDto;
import dongguknuri.dto.response.CommentResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.board.CommentRepository;
import dongguknuri.repository.board.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        return commentRepository.findByPost(post).stream()
                .map(comment -> CommentResponseDto.of(
                        comment.getCommentId(),
                        comment.getContent(),
                        comment.getUser().getUserId(),
                        comment.getPost().getPostId(),
                        comment.getCreatedAt()
                )).collect(Collectors.toList());
    }

    @Transactional
    public boolean createComment(CreateCommentDto createCommentDto) {
        Post post = postRepository.findById(createCommentDto.postId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        User user = userRepository.findById(createCommentDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        commentRepository.save(Comment.builder()
                .content(createCommentDto.content())
                .user(user)
                .post(post)
                .build());

        return Boolean.TRUE;
    }
}
