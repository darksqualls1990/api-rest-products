package com.springboot.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the categorias database table.
 * 
 */
@Entity
@Table(name = "Categorias")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(name = "foto")
	private String foto;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	// bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name = "categoria_padre")
	@JsonBackReference
	private Category parentCategory;

	// bi-directional many-to-one association to Categoria
	@OneToMany(mappedBy = "parentCategory")
	@JsonBackReference
	private List<Category> categorias;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product> productos;

	public Category() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Category getParentCategory() {
		return this.parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(List<Category> categorias) {
		this.categorias = categorias;
	}

	public Category addCategoria(Category categoria) {
		getCategorias().add(categoria);
		categoria.setParentCategory(this);

		return categoria;
	}

	public Category removeCategoria(Category categoria) {
		getCategorias().remove(categoria);
		categoria.setParentCategory(null);
		return categoria;
	}

	public List<Product> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Product> productos) {
		this.productos = productos;
	}

	public Product addProducto(Product producto) {
		getProductos().add(producto);
		producto.setCategory(this);
		return producto;
	}

	public Product removeProducto(Product producto) {
		getProductos().remove(producto);
		producto.setCategory(null);
		return producto;
	}

}