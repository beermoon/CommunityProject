package kr.co.choi.community.controller;

import jakarta.validation.Valid;
import kr.co.choi.community.controller.dto.PostCreateRequest;
import kr.co.choi.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateRequest request) {
        Long postId = postService.createPost(request);
        return ResponseEntity.ok("게시글 등록 성공. id=" + postId);
    }

    public ReponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

}
