package io.github.seonrizee.scheduler.global.entity;

import io.github.seonrizee.scheduler.domain.user.entity.User;

public interface Ownable {
    User getUser();
}
