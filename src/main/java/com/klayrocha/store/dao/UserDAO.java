package com.klayrocha.store.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.klayrocha.store.mapper.PermissionRowMapper;
import com.klayrocha.store.mapper.UserRowMapper;
import com.klayrocha.store.model.Permission;
import com.klayrocha.store.model.User;

@Repository
public class UserDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User create(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO USER (account_non_expired, account_non_locked, credentials_non_expired, ");
		sb.append(" date,enabled,full_name,password,user_name) " );
		sb.append(" VALUES (?,?,?,?,?,?,?,?) ");
		jdbcTemplate.update(sb.toString(),1,1,1,new Date(),1,user.getFullName(),user.getPassword(),user.getUsername());
		return login(user.getUsername(), user.getPassword());
	}

	public User login(final String userName, String password) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM USER ");
		sb.append(" WHERE user_name = ?  ");
		sb.append(" AND password = ? ");
		try {
			User user = jdbcTemplate.queryForObject(sb.toString(), new UserRowMapper(), userName, password);
			user.setPermissions(getPermissions(user.getId()));
			return user;
		} catch(EmptyResultDataAccessException ex){
		    return null;
		}
	}	
	
	
	public User findByUsername(String userName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM USER ");
		sb.append(" WHERE user_name = ?  ");
		try {
			User user = jdbcTemplate.queryForObject(sb.toString(), new UserRowMapper(), userName);
			user.setPermissions(getPermissions(user.getId()));
			return user;
		} catch(EmptyResultDataAccessException ex){
		    return null;
		}
	}
	
	public List<Permission> getPermissions(final Long idUser) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM PERMISSION P ");
	    sb.append(" INNER JOIN USER_PERMISSION UP ON (UP.ID_PERMISSION = P.ID) ");
		sb.append(" AND UP.ID_USER = ? ");
		try {
			return jdbcTemplate.query(sb.toString(), new PermissionRowMapper(), idUser);
		} catch(EmptyResultDataAccessException ex){
		    return null;
		}
	}
	
	public void addPermission(final long idUser, final long idPermission) {
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO USER_PERMISSION (ID_USER, ID_PERMISSION) VALUES (?,?) ");
		jdbcTemplate.update(sb.toString(),idUser,idPermission);
	}
}
