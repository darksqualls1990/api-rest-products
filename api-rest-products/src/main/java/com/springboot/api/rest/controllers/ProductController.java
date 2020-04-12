package com.springboot.api.rest.controllers;

import java.util.List;

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
import com.springboot.api.model.Product;
import com.springboot.api.model.RequestProduct;
import com.springboot.api.services.CategoryService;
import com.springboot.api.services.ProductService;

/**
 * REST controller for products
 * 
 * @author dfsalinas
 *
 */
@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * Supports all products queries
	 * 
	 * @return
	 */
	@GetMapping("/public/getAll")
	public ResponseEntity<?> getProducts() {
		List<Product> products = productService.findAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/**
	 * Supports product queries by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/public/getProduct/{id}")
	public ResponseEntity<?> getProductsById(@PathVariable Integer id) {
		Product product = productService.findProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Product not exist", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Supports product queries by category Id
	 * 
	 * @param idCategory
	 * @param pageNo
	 * @param pageSize
	 * @param sortBy
	 * @return
	 */
	@GetMapping("/public/getProductsByCategory/{idCategory}")
	public ResponseEntity<?> getProductsByCategory(@PathVariable Integer idCategory,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "codigo") String sortBy) {

		GenericResponse<Product> response = new GenericResponse<>();
		response.setMessage("Products not found, missing data");
		response.setTotalPages(0);

		if (idCategory != null) {
			Category category = new Category();
			category.setCodigo(idCategory);
			pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
			Page<Product> products = productService.findProductsByCategory(category, pageNo, pageSize, sortBy);

			if (products != null && products.hasContent()) {
				response.setElements(products.getContent());
				response.setTotalPages(products.getTotalPages());
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
	 * Handles creating products
	 * 
	 * @param requestProduct
	 * @return
	 */
	@PostMapping("create")
	public ResponseEntity<?> createProduct(@RequestBody RequestProduct requestProduct) {
		if (requestProduct != null && requestProduct.getCategoria() != null) {
			Category category = categoryService.findCategoryById(requestProduct.getCategoria());
			if (category != null) {
				List<Category> parentCategories = category.getCategorias();
				if (parentCategories != null && parentCategories.isEmpty()) {
					Product productNew = new Product();
					productNew.setCategory(category);
					productNew.setDescripcion(requestProduct.getDescripcion());
					productNew.setNombre(requestProduct.getNombre());
					productNew.setPeso(requestProduct.getPeso());
					productNew.setPrecioUsd(requestProduct.getPrecioUsd());
					productNew.setFoto1(requestProduct.getFoto1());
					productNew.setFoto2(requestProduct.getFoto2());
					productNew.setFoto3(requestProduct.getFoto3());
					productNew = productService.createProduct(productNew);
					return new ResponseEntity<Product>(productNew, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>("Product not created, category not valid", HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>("Product not created, category not exist", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>("Product not created, missing data", HttpStatus.BAD_REQUEST);
	}
}
