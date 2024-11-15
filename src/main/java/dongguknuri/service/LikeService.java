package dongguknuri.service;

import dongguknuri.domain.User;
import dongguknuri.domain.board.Like;
import dongguknuri.domain.board.Post;
import dongguknuri.dto.request.CreateLikeDto;
import dongguknuri.dto.request.DeleteLikeDto;
import dongguknuri.exception.CommonException;
import dongguknuri.exception.ErrorCode;
import dongguknuri.repository.UserRepository;
import dongguknuri.repository.board.LikeRepository;
import dongguknuri.repository.board.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public boolean addLike(CreateLikeDto createLikeDto) {
        User user = userRepository.findById(createLikeDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Post post = postRepository.findById(createLikeDto.postId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new CommonException(ErrorCode.ALREADY_EXISTS);
        }

        likeRepository.save(Like.builder().user(user).post(post).build());
        return Boolean.TRUE;
    }

    @Transactional
    public boolean removeLike(DeleteLikeDto deleteLikeDto) {
        User user = userRepository.findById(deleteLikeDto.userId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Post post = postRepository.findById(deleteLikeDto.postId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_POST));

        Like like = likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LIKE));
        likeRepository.delete(like);

        return Boolean.TRUE;
    }
}
