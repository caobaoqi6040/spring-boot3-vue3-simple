package dev.caobaoqi6040.backend.modules.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User
 *
 * @author caobaoqi6040
 * @since 2025/9/24 10:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private Long id;
	private String username;
	private String email;
	private String password;
	private String avatar;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
