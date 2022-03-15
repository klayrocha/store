package com.klayrocha.store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.klayrocha.store.model.Permission;

public class PermissionRowMapper implements RowMapper<Permission> {

	@Override
	public Permission mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final Permission permission = new Permission();
		permission.setId(rs.getLong("ID"));
		permission.setDescription(rs.getString("DESCRIPTION"));
		return permission;
	}
}
