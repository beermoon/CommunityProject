package kr.co.choi.community.service;


import kr.co.choi.community.controller.dto.PostCreateRequest;
import kr.co.choi.community.controller.dto.PostResponse;
import kr.co.choi.community.domain.member.Member;
import kr.co.choi.community.domain.post.Post;
import kr.co.choi.community.repository.MemberRepository;
import kr.co.choi.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createPost(PostCreateRequest request) {
        // 글쓴이 찾기
        Member writer = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(writer)
                .build();

        Post saved = postRepository.save(post);
        return saved.getId();

    }

    public List<PostResponse> getAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getWriter() !=null ? post.getWriter().getUsername() : null
                ))
                .toList();
    }

    public PostResponse getOne(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter() !=null ? post.getWriter().getUsername() : null
        );
    }

}
