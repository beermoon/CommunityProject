package kr.co.choi.community.controller;


import jakarta.validation.Valid;
import kr.co.choi.community.controller.dto.LoginRequest;
import kr.co.choi.community.controller.dto.SignUpRequest;
import kr.co.choi.community.domain.member.Member;
import kr.co.choi.community.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Member loginMember = memberService.login(request.getUsername(),request.getPassword());

        // 나중에 그냥 성공했다고만 응답
        // 나중에 여기서 JWT 토큰을 발급하면 됨
        return ResponseEntity.ok("로그인 성공 : " + loginMember.getNickname());
    }


}
