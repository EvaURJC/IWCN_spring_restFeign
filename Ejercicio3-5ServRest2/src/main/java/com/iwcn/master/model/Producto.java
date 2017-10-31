package com.iwcn.master.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Producto {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public String codigo;
	
	public String nombre;
	
	public String descripcion;
	
	public Integer precio;
	
	protected Producto () {
		
	}
	
}
