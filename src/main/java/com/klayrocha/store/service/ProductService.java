package com.klayrocha.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klayrocha.store.dao.ProductDAO;
import com.klayrocha.store.exception.ResourceNotFoundException;
import com.klayrocha.store.model.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductDAO productDAO;

	public List<Product> getAllProducts(final int page, final int limit) {
		
		List<Product> result =  productDAO.getAllProducts(page, limit);
		if(result == null) {
			throw new ResourceNotFoundException("No records found");
		}
		return result;
	}

	public void create(Product product) {
		productDAO.create(product);
	}

	public Product update(Product product) {
		return productDAO.update(product);
	}

	public void delete(Integer id) {
		productDAO.delete(id);
	}

	public Product getById(Integer id, Product product) {
		product = productDAO.getById(id);
		if(product == null) {
			 throw new ResourceNotFoundException("No records found for this ID");
		}
		return product;
	}
}
