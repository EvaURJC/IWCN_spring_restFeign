package com.iwcn.master.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwcn.master.model.Producto;
import com.iwcn.master.repositories.ProductoRepository;

@Service
public class DatabaseServices {
	
	@Autowired
	private ProductoRepository productRepository;
	
	@PostConstruct
	public void initDatabase() {	

	}
	public ArrayList<Producto> getProductos () {
		ArrayList<Producto> productos = new ArrayList<>();
		for (Producto p : productRepository.selectAll()) {
			productos.add(p);
		}
		return productos;
	}
	
	public void addProducto (Producto pi) {
		productRepository.save(pi);
	}
	
	public Producto findId (int index) {
		return productRepository.findById(index);
	}
	
	public void deleteProducto (int id, String codigo, String nombre, String descripcion, Integer precio) {
		Producto pi = new Producto(id, codigo, nombre, descripcion, precio);
		productRepository.delete(pi);
	}
	
	public void setProducto (String codigo, String nombre, String descripcion, Integer precio, int id) {
		productRepository.setNewProducto(codigo, nombre, descripcion, precio, id);
	}
	
}
