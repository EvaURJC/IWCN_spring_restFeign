package com.iwcn.master.services.interfaces;

import org.springframework.web.bind.annotation.RequestBody;

import com.iwcn.master.model.Producto;

import feign.Headers;
import feign.RequestLine;

public interface IProductoFeign {

	@RequestLine("GET")
	Producto[] getListaProducto();
	
	@RequestLine("POST")
    @Headers("Content-Type: application/json")
    void addProducto(@RequestBody Producto p);
	
	@RequestLine("GET")
	Producto getProducto();
	
	@RequestLine("GET")
    void deleteProducto();
	
	@RequestLine("POST")
	@Headers("Content-Type: application/json")
    void modProducto(@RequestBody Producto p);
}
