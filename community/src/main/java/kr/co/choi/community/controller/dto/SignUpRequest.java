package kr.co.choi.community.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {


    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
}
