package com.springboot.api.model;

import java.math.BigDecimal;

/**
 * Class representing the request for the Product
 * 
 * @author dfsalinas
 *
 */
public class RequestProduct {

	private String descripcion;

	private String nombre;

	private String peso;

	private String foto1;

	private String foto2;

	private String foto3;

	private BigDecimal precioUsd;

	private Integer categoria;

	public RequestProduct() {
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public BigDecimal getPrecioUsd() {
		return precioUsd;
	}

	public void setPrecioUsd(BigDecimal precioUsd) {
		this.precioUsd = precioUsd;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

}
