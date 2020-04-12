package com.springboot.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.api.model.Category;
import com.springboot.api.model.Product;

/**
 * DAO that handles persistence operations for products
 * 
 * @author dfsalinas
 *
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

	/**
	 * Get products by category and pagination
	 * 
	 * @param category product category
	 * @param paging   object of pagination and ordering
	 * @return
	 */
	@Query("select p from Product p where p.category = :category")
	Page<Product> findProductsByCategory(Category category, Pageable paging);

}
