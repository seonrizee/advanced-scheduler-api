package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;

public interface SessionService {

    UserProfileResponse login(SessionCreateRequest requestDto);
}
