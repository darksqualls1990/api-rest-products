package com.springboot.api.services;

import org.springframework.data.domain.Page;

import com.springboot.api.model.Category;

/**
 * Interface that has all the methods for the category CRUD
 * 
 * @author dfsalinas
 *
 */
public interface CategoryService {

	/**
	 * Get category by Id
	 * 
	 * @param id
	 * @return
	 */
	public Category findCategoryById(Integer id);

	/**
	 * It allows to consult categories by parent category, pagination and ordering
	 * 
	 * @param parentCategory parent category
	 * @param pageNo         page number
	 * @param pageSize       Size of elements on page
	 * @param sortBy         ordering
	 * @return
	 */
	public Page<Category> findCategoriesByParentCategory(Integer categoryId, Integer pageNo, Integer pageSize,
			String sortBy);

	/**
	 * It allows to consult main categories, pagination and ordering
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	public Page<Category> findMainCategories(Integer pageNo, Integer pageSize, String sortBy);

	/**
	 * Create categorie
	 * 
	 * @param categoryNew
	 * @return
	 */
	public Category createCategory(Category categoryNew);

	/**
	 * Delete categorie
	 * 
	 * @param categoryName
	 */
	public void deleteCategory(Category categoryNew);

}
