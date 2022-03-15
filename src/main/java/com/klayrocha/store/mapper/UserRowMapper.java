package com.klayrocha.store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.klayrocha.store.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final User user = new User();

		user.setId(rs.getLong("ID"));
		user.setFullName(rs.getString("FULL_NAME"));
		user.setUsername(rs.getString("USER_NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setDate(LocalDate.now());
		return user;
	}
}