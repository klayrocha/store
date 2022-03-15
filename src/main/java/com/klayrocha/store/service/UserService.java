package com.klayrocha.store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klayrocha.store.dao.UserDAO;
import com.klayrocha.store.exception.StoreException;
import com.klayrocha.store.model.User;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserDAO dao;
	Logger logger = LoggerFactory.getLogger(UserService.class);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException(" Username " + username + " not found");
		}
	}

	public User create(User user) {
		User userRet = null;
		try {
			validate(user);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRet = dao.create(user);
			dao.addPermission(userRet.getId(), 1l);
		} catch (DataIntegrityViolationException e) {
			logger.error("customer/create : Error "+e);
			throw new StoreException("User already registered");
		} catch (StoreException e) {
			logger.error("customer/create : Error "+e);
			throw new StoreException(e.toString());
		} catch (Exception e) {
			logger.error("customer/create : Error "+e);
			throw new StoreException("Error while inserting, please contact the administrator");
		}
		return userRet;
	}

	private void validate(User user) {
		if (user == null) {
			throw new StoreException("The User cannot be empty");
		}

		if (user.getUsername() == null || user.getUsername().trim().equals("")) {
			throw new StoreException("The username cannot be empty");
		}

		if (user.getFullName() == null || user.getFullName().trim().equals("")) {
			throw new StoreException("The FullName cannot be empty");
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			throw new StoreException("The Password cannot be empty");
		}

	}

}
