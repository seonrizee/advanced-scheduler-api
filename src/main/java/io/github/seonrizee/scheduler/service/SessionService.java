package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;

public interface SessionService {

    UserProfileResponse login(SessionCreateRequest requestDto);

}
