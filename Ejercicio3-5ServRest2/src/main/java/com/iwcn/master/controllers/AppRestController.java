package com.iwcn.master.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iwcn.master.model.Producto;
import com.iwcn.master.services.DatabaseServices;

@RestController
public class AppRestController {
	
	@Autowired
	private DatabaseServices dbServices;
	
	// Vista que contiene Lista de Productos
	@RequestMapping(value = "/principal/list", method = RequestMethod.GET)
    public List<Producto> listaProducto() {
		return dbServices.getProductos();
    }
	
	// Vista que muestra que el producto se ha añadido
	@RequestMapping(value = "/principal/fin", method = RequestMethod.POST)
    public void nuevoProducto2(@RequestBody Producto pi) {
		dbServices.addProducto(pi);
    }
	
	// Vista del producto en si
    @RequestMapping(value = "/principal/product/{optio}", method = RequestMethod.GET)
	public Producto mostrarProducto(@PathVariable String optio) {
    	int index = Integer.parseInt(optio);
    	Producto producto = dbServices.findId(index);
    	return producto;
    }
    
    // Vista que contiene Lista de Productos
    @RequestMapping(value = "/principal/borrar/{optio}", method = RequestMethod.GET)
    public void borrarProducto(@PathVariable String optio) {
    	int index = Integer.parseInt(optio);
    	Producto pi = dbServices.findId(index);
    	dbServices.deleteProducto(pi.getId(), pi.getCodigo(), pi.getNombre(), pi.getDescripcion(), pi.getPrecio());
    } 
    
    // Vista que muestra que el producto se ha editado
    @RequestMapping(value = "/principal/modificar", method = RequestMethod.POST)
    public void modificarProducto(@RequestBody Producto pi) {
    	dbServices.setProducto (pi.getCodigo(), pi.getNombre(), pi.getDescripcion(), pi.getPrecio(), pi.getId());
    }
    
}