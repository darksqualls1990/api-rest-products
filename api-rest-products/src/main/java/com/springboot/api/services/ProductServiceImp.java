package com.springboot.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.api.dao.ProductDao;
import com.springboot.api.model.Category;
import com.springboot.api.model.Product;

/**
 * Implementation class for Product CRUD
 * 
 * @author dfsalinas
 *
 */
@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	@Override
	public Product findProductById(Integer id) {
		Optional<Product> product = productDao.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		return null;
	}

	@Override
	public Product createProduct(Product productNew) {
		if (productNew != null) {
			return productDao.save(productNew);
		}
		return new Product();
	}

	@Override
	public Page<Product> findProductsByCategory(Category category, Integer pageNo, Integer pageSize, String sortBy) {
		if (category != null && category.getCodigo() != null) {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<Product> pagedResult = productDao.findProductsByCategory(category, paging);
			if (pagedResult.hasContent()) {
				return pagedResult;
			}
		}
		return null;
	}

}
