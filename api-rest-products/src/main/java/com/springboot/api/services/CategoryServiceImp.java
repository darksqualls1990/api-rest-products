package com.springboot.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.api.dao.CategoryDao;
import com.springboot.api.model.Category;

/**
 * Implementation class for category CRUD
 * 
 * @author dfsalinas
 *
 */
@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public Page<Category> findCategoriesByParentCategory(Integer categoryId, Integer pageNo, Integer pageSize,
			String sortBy) {
		if (categoryId != null) {
			Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
			Page<Category> pagedResult = categoryDao.findCategoriesByParentCategory(categoryId, paging);
			if (pagedResult.hasContent()) {
				return pagedResult;
			}
		}
		return null;
	}

	@Override
	public Category findCategoryById(Integer id) {
		Optional<Category> category = categoryDao.findById(id);
		if (category.isPresent()) {
			return category.get();
		}
		return null;
	}

	@Override
	public Category createCategory(Category categoryNew) {
		if (categoryNew != null) {
			return categoryDao.save(categoryNew);
		}
		return new Category();
	}

	@Override
	public Page<Category> findMainCategories(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Category> pagedResult = categoryDao.findMainCategories(paging);
		if (pagedResult.hasContent()) {
			return pagedResult;
		}
		return null;
	}

	@Override
	public void deleteCategory(Category categoryNew) {
		categoryDao.delete(categoryNew);
	}
	
	

}
