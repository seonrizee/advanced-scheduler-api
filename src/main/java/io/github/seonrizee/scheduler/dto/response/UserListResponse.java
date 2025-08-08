package io.github.seonrizee.scheduler.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserListResponse {

    private final List<UserProfileResponse> users;
}
