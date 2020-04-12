package com.springboot.api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.api.model.Category;

/**
 * DAO that handles persistence operations for categories
 * 
 * @author dfsalinas
 *
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {

	/**
	 * Get categories by parent category
	 * 
	 * @param category categoria padre
	 * @param paging   objeto de paginación y ordenamiento
	 * @return
	 */
	@Query("select c from Category c where c.parentCategory.codigo = :categoryId and c.codigo <> :categoryId")
	Page<Category> findCategoriesByParentCategory(Integer categoryId, Pageable paging);

	/**
	 * Get the main categories
	 * 
	 * @param paging
	 * @return
	 */
	@Query("select c from Category c where c.codigo = c.parentCategory.codigo")
	Page<Category> findMainCategories(Pageable paging);

}
