package com.springboot.api.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.model.Category;
import com.springboot.api.model.GenericResponse;
import com.springboot.api.model.RequestCategory;
import com.springboot.api.services.CategoryService;

/**
 * REST controller for categories
 * 
 * @author dfsalinas
 *
 */
@RestController
@RequestMapping("category")
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * Handles creating categories
	 * 
	 * @param requestCategory
	 * @return
	 */
	@PostMapping("create")
	public ResponseEntity<?> createCategory(@RequestBody RequestCategory requestCategory) {
		if (requestCategory != null && requestCategory.getCategoriaPadre() != null) {
			Category parentCategory = categoryService.findCategoryById(requestCategory.getCategoriaPadre());
			if (parentCategory != null) {
				Category categoryNew = new Category();
				categoryNew.setNombre(requestCategory.getNombre());
				categoryNew.setFoto(requestCategory.getFoto());
				categoryNew.setParentCategory(parentCategory);
				categoryNew = categoryService.createCategory(categoryNew);
				return new ResponseEntity<Category>(categoryNew, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Category not created, parent category not valid", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Category not created, missing data", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Supports categories by parent category queries
	 * 
	 * @param idCategory
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@GetMapping("/public/getCategoriesByParent/{idCategory}")
	public ResponseEntity<?> getCategoriesByParentCategory(@PathVariable Integer idCategory,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "codigo") String sortBy) {

		GenericResponse<Category> response = new GenericResponse<Category>();
		response.setMessage("Categories not found, missing data");
		response.setTotalPages(0);

		if (idCategory != null) {
			pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
			Page<Category> categories = categoryService.findCategoriesByParentCategory(idCategory, pageNo, pageSize,
					sortBy);
			if (categories != null && categories.hasContent()) {
				response.setElements(categories.getContent());
				response.setTotalPages(categories.getTotalPages());
				response.setMessage("OK");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("No content");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Supports main category queries
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@GetMapping("/public/getMainCategories")
	public ResponseEntity<?> getMainCategories(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "codigo") String sortBy) {

		GenericResponse<Category> response = new GenericResponse<Category>();
		response.setMessage("No content");
		response.setTotalPages(0);
		pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
		Page<Category> categories = categoryService.findMainCategories(pageNo, pageSize, sortBy);

		if (categories != null && categories.hasContent()) {
			response.setElements(categories.getContent());
			response.setTotalPages(categories.getTotalPages());
			response.setMessage("OK");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Handles deleting categories
	 * 
	 * @param requestCategory
	 * @return
	 */
	@PostMapping("/delete")
	public ResponseEntity<?> deleteCategoryByName(@RequestBody RequestCategory requestCategory) {
		Category category = new Category();
		category.setCodigo(requestCategory.getCodigo());
		Category parent = new Category();
		category.setNombre(requestCategory.getNombre());
		parent.setCodigo(requestCategory.getCategoriaPadre());
		category.setParentCategory(parent);
		categoryService.deleteCategory(category);
		return new ResponseEntity<>("Category is deleted successsfully", HttpStatus.OK);
	}

}
