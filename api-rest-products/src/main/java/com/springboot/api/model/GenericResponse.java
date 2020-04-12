package com.springboot.api.model;

import java.util.List;

/**
 * Class for generics response
 * 
 * @author dfsalinas
 *
 */
public class GenericResponse<T> {

	private List<T> elements;

	private Integer totalPages;

	private String message;

	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
