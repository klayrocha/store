package com.klayrocha.store.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klayrocha.store.jwt.JwtTokenProvider;
import com.klayrocha.store.model.User;
import com.klayrocha.store.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> login(@RequestBody User user) {
		try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
				User userRet = (User)userService.loadUserByUsername(user.getUsername());
				String token = "";
				token = jwtTokenProvider.createToken(userRet.getUsername(), userRet.getRoles());
				Map<Object, Object> model = new HashMap<>();
				userRet.setPassword(null);
				model.put("user", userRet);
				model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			logger.error("Ivalid username/password", e.toString());
			throw new BadCredentialsException("Ivalid username/password");
		}
	}

}
