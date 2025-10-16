package dev.caobaoqi6040.backend.modules.user.domain.response;

import java.time.LocalDateTime;

public record UserInfoVo(
	String username,
	String email,
	String avatar,
	LocalDateTime createTime,
	LocalDateTime updateTime
) {
}
