package io.github.seonrizee.scheduler.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequest {

    @NotBlank(message = "사용자 이름은 비어있을 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$")
    private final String username;

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    @NotBlank(message = "이메일은 비어있을 수 없습니다.")
    private final String email;
}
