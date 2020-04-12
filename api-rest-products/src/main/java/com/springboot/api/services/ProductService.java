package com.springboot.api.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.api.model.Category;
import com.springboot.api.model.Product;

/**
 * Interface containing all the methods for the CRUD of products
 * 
 * @author dfsalinas
 *
 */
public interface ProductService {

	/**
	 * Permite consultar todos los productos
	 * 
	 * @return all product list
	 */
	public List<Product> findAllProducts();

	/**
	 * Get Product by Id
	 * 
	 * @param id
	 * @return producto encontrado
	 */
	public Product findProductById(Integer id);

	/**
	 * Create Product
	 * 
	 * @param productNew
	 * @return product created
	 */
	public Product createProduct(Product productNew);

	/**
	 * It allows to consult the products by category, pagination and ordering
	 * 
	 * @param category categoria del producto
	 * @param pageNo   Numero de pagina
	 * @param pageSize Tamaño de elementos en pagina
	 * @param sortBy   factor de ordenamiento
	 * @return
	 */
	public Page<Product> findProductsByCategory(Category category, Integer pageNo, Integer pageSize, String sortBy);

}
