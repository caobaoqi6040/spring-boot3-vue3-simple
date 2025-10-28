package dev.caobaoqi6040.backend.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Slf4j
public class RestfulAuthenticationEntryPointHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

	@Override
	public void commence(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authException) {
		log.info("RestfulAuthenticationEntryPointHandler.commence msg {}", authException.getLocalizedMessage());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

	@Override
	public void handle(
		HttpServletRequest request,
		HttpServletResponse response,
		AccessDeniedException accessDeniedException) {
		log.info("RestfulAuthenticationEntryPointHandler.handle msg {}", accessDeniedException.getLocalizedMessage());
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	}

}
