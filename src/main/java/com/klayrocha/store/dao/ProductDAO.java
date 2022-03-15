package com.klayrocha.store.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.klayrocha.store.mapper.ProductRowMapper;
import com.klayrocha.store.model.Product;

@Repository
public class ProductDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(final DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Product> getAllProducts(final int page, final int limit) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT P.*,PT.ID AS PRODUCT_TYPE_ID, PT.DESCRIPTION AS PRODUCT_TYPE_DESCRIPTION FROM PRODUCT P ");
		sb.append(" INNER JOIN PRODUCT_TYPE PT ON (PT.ID = P.ID_PRODUCT_TYPE) ");
		sb.append(" ORDER BY P.NAME ");
		if (page > 0 || limit > 0) {
			sb.append(" LIMIT ? , ? ");
		}
		try {
			return jdbcTemplate.query(sb.toString(), new ProductRowMapper(), page, limit);
		} catch(EmptyResultDataAccessException ex){
		    return null;
		}
	}

	public void create(Product product) {
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO  PRODUCT (NAME,QUANTITY,ID_PRODUCT_TYPE) ");
		sb.append(" VALUES (?,?,?) ");
		jdbcTemplate.update(sb.toString(), product.getName(), product.getQuantity(), product.getProductType().getId());
	}

	public Product update(Product product) {
		StringBuffer sb = new StringBuffer();
		sb.append(" UPDATE PRODUCT  SET ");
		sb.append(" NAME = ?, QUANTITY = ?, ID_PRODUCT_TYPE = ? ");
		sb.append(" WHERE ID = ?");

		jdbcTemplate.update(sb.toString(), product.getName(), product.getQuantity(), product.getProductType().getId(),product.getId());

		return getById(product.getId());
	}

	public Product getById(final int id) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT P.*,PT.ID AS PRODUCT_TYPE_ID, PT.DESCRIPTION AS PRODUCT_TYPE_DESCRIPTION FROM PRODUCT P ");
		sb.append(" INNER JOIN PRODUCT_TYPE PT ON (PT.ID = P.ID_PRODUCT_TYPE) ");
		sb.append(" WHERE P.ID = ? ");
		try {
			return jdbcTemplate.queryForObject(sb.toString(), new ProductRowMapper(), id);
		} catch(EmptyResultDataAccessException ex){
		    return null;
		}
	}

	public void delete(Integer id) {
		 jdbcTemplate.update("DELETE FROM PRODUCT WHERE ID = ? ",id);
	}
	

}
