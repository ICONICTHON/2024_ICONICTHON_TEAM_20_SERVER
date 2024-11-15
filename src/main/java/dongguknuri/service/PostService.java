package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.board.Board;
import dongguknuri.domain.board.Post;
import dongguknuri.dto.request.CreatePostDto;
import dongguknuri.dto.request.UpdatePostDto;
import dongguknuri.dto.response.PostResponseDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.board.BoardRepository;
import dongguknuri.repository.board.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostResponseDto.of(
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getUserId(),
                        post.getBoard().getBoardId(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getUserId())
                .boardId(post.getBoard().getBoardId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    @Transactional
    public boolean createPost(CreatePostDto createPostDto) {
        User user = userRepository.findById(createPostDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Board board = boardRepository.findById(createPostDto.boardId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_BOARD));

        postRepository.save(Post.builder()
                .title(createPostDto.title())
                .content(createPostDto.content())
                .user(user)
                .board(board)
                .build());

        return Boolean.TRUE;
    }

    @Transactional
    public boolean updatePost(UpdatePostDto updatePostDto){
        Post post = postRepository.findById(updatePostDto.id())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        post.updatePost(updatePostDto.title(), updatePostDto.content());
        return Boolean.TRUE;
    }

    @Transactional
    public boolean deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));
        postRepository.delete(post);

        return Boolean.TRUE;
    }
}
