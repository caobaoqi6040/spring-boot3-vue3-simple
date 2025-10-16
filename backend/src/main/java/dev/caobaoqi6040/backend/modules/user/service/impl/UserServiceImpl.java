package dev.caobaoqi6040.backend.modules.user.service.impl;

import dev.caobaoqi6040.backend.modules.user.entity.User;
import dev.caobaoqi6040.backend.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService, CommandLineRunner {

	private final Faker faker;

	private final List<User> users = new ArrayList<>();

	public UserServiceImpl(Faker faker) {
		this.faker = faker;
	}

	@Override
	public void run(String... args) {
		if (!users.isEmpty()) {
			log.warn("user list data already exist. skip mock");
			return;
		}
		for (int i = 0; i < 10; i++) {
			users.add(User.builder()
				.id((long) i)
				.username(faker.funnyName().name())
				.password("***************")
				.email(faker.phoneNumber().phoneNumber().replace(" ", "") + "@gmail.com")
				.avatar(faker.avatar().image())
				.createTime(LocalDateTime.now())
				.updateTime(LocalDateTime.now())
				.build());
		}
	}

	@Override
	public List<User> list() {
		return users;
	}

}
