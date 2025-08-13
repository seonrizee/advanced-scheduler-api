package io.github.seonrizee.scheduler.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionCreateRequest {

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}$",
            message = "비밀번호는 8~16자 사이의 영문, 숫자, 특수문자를 포함해야 합니다.")
    private final String password;
}
