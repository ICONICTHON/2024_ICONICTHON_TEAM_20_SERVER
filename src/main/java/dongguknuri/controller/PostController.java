package dongguknuri.controller;

import dongguknuri.dto.global.ResponseDto;
import dongguknuri.dto.request.CreatePostDto;
import dongguknuri.dto.request.UpdatePostDto;
import dongguknuri.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseDto<?> getAllPosts() {
        return ResponseDto.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseDto<?> getPostById(@PathVariable Long postId) {
        return ResponseDto.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseDto<?> createPost(@RequestBody CreatePostDto createPostDto) {
        return ResponseDto.created(postService.createPost(createPostDto));
    }

    @PutMapping("/{postId}")
    public ResponseDto<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostDto updatePostDto) {
        return ResponseDto.ok(postService.updatePost(updatePostDto));
    }

    @DeleteMapping("/{postId}")
    public ResponseDto<?> deletePost(@PathVariable Long postId) {
        return ResponseDto.ok(postService.deletePost(postId));
    }
}
