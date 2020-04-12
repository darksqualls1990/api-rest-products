package com.springboot.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name = "Productos")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	private String descripcion;

	private String nombre;

	private String peso;

	private String foto1;

	private String foto2;

	private String foto3;

	private BigDecimal precioUsd;

	// bi-directional many-to-one association to Categoria
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoria", nullable = false)
	@JsonSerialize
	private Category category;

	public Product() {
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto1() {
		return this.foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return this.foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return this.foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeso() {
		return this.peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecioUsd() {
		return this.precioUsd;
	}

	public void setPrecioUsd(BigDecimal precioUsd) {
		this.precioUsd = precioUsd;
	}

	
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}