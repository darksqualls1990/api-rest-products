package com.springboot.api.model;

/**
 * Class representing the request for the Category
 * 
 * @author dfsalinas
 *
 */
public class RequestCategory {

	private Integer codigo;

	private String foto;

	private String nombre;

	private Integer categoriaPadre;

	public RequestCategory() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCategoriaPadre() {
		return categoriaPadre;
	}

	public void setCategoriaPadre(Integer categoriaPadre) {
		this.categoriaPadre = categoriaPadre;
	}

}
