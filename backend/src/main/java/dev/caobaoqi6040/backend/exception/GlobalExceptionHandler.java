package dev.caobaoqi6040.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * GlobalExceptionHandler
 *
 * @author caobaoqi6040
 * @since 2025/9/21 19:57
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ProblemDetail handleBusinessException(BusinessException ex) {

		log.warn("[business exception] msg {}", ex.getLocalizedMessage(), ex);

		ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		detail.setTitle("------- business exception -------");
		detail.setType(URI.create("http://localhost:8080/business/help"));
		detail.setProperty("time-stamp", LocalDateTime.now());
		detail.setDetail(ex.getLocalizedMessage());

		return detail;
	}

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleRuntimeException(Exception ex) {

		log.warn("[exception msg] {}", ex.getLocalizedMessage(), ex);

		ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		detail.setTitle("------- exception -------");
		detail.setType(URI.create("http://localhost:8080/help"));
		detail.setProperty("time-stamp", LocalDateTime.now());
		detail.setDetail("你干嘛 哎呦 ~ 🐔");

		return detail;
	}

}
