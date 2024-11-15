package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreateLikeDto;
import dongguknuri.dto.request.DeleteLikeDto;
import dongguknuri.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{postId}")
    public ResponseDto<?> addLike(@PathVariable Long postId, @RequestBody CreateLikeDto createLikeDto) {
        return ResponseDto.created(likeService.addLike(createLikeDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseDto<?> removeLike(@PathVariable Long postId, @RequestBody DeleteLikeDto deleteLikeDto) {
        return ResponseDto.ok(likeService.removeLike(deleteLikeDto));
    }
}
