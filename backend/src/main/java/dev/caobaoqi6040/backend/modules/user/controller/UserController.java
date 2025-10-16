package dev.caobaoqi6040.backend.modules.user.controller;

import dev.caobaoqi6040.backend.modules.user.domain.UserStruct;
import dev.caobaoqi6040.backend.modules.user.domain.response.UserInfoVo;
import dev.caobaoqi6040.backend.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 *
 * @author caobaoqi6040
 * @since 2025/10/9 15:14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService service;
	private final UserStruct struct;

	@GetMapping
	public ResponseEntity<List<UserInfoVo>> list() {
		var list = service.list().stream()
			.map(struct::user2UserInfo)
			.toList();
		return ResponseEntity.ok(list);
	}
}
