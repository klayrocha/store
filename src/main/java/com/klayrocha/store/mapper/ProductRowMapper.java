package com.klayrocha.store.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.klayrocha.store.model.Product;
import com.klayrocha.store.model.ProductType;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final Product product = new Product();
		final ProductType productType = new ProductType();

		product.setId(rs.getInt("ID"));
		product.setName(rs.getString("NAME"));
		product.setQuantity(rs.getInt("QUANTITY"));
		productType.setId(rs.getInt("PRODUCT_TYPE_ID"));
		productType.setDescription(rs.getString("PRODUCT_TYPE_DESCRIPTION"));
		product.setProductType(productType);
		return product;
	}
}
