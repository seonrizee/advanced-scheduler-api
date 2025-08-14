package io.github.seonrizee.scheduler.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 회원가입을 위한 요청 DTO.
 */
@Getter
@AllArgsConstructor
public class UserRegisterRequest {

    /**
     * 사용자 이름.
     * 4~15자의 영문 대소문자와 숫자로 구성되어야 합니다.
     */
    @NotBlank(message = "사용자 이름은 비어있을 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$", message = "사용자 이름은 4~15자의 영문 대소문자와 숫자로만 구성되어야 합니다.")
    private final String username;

    /**
     * 이메일.
     * 유효한 이메일 형식이어야 합니다.
     */
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private final String email;

    /**
     * 비밀번호.
     * 8~16자 사이의 영문, 숫자, 특수문자를 포함해야 합니다.
     */
    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$",
            message = "비밀번호는 8~16자 사이의 영문, 숫자, 특수문자를 포함해야 합니다.")
    private final String password;
}
