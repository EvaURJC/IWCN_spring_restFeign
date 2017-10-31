package com.iwcn.master.services;

import org.springframework.stereotype.Service;

import com.iwcn.master.model.Producto;
import com.iwcn.master.services.interfaces.Data;
import com.iwcn.master.services.interfaces.IProductoFeign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;

@Service
public class FeignDataService implements Data{

	@Override
	public Producto[] callLista () {
		IProductoFeign pro = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new JacksonEncoder())
				  .decoder(new JacksonDecoder())
				  .target(IProductoFeign.class, "http://localhost:8080/principal/list");
		return pro.getListaProducto();
	}
	
	@Override
	public void callNuevo (Producto pi) {
		IProductoFeign pro = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new JacksonEncoder())
				  .decoder(new JacksonDecoder())
				  .target(IProductoFeign.class, "http://localhost:8080/principal/fin");
		
		pro.addProducto(pi);
	}
	
	@Override
	public Producto callProducto (int index) {
		IProductoFeign pro = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new JacksonEncoder())
				  .decoder(new JacksonDecoder())
				  .target(IProductoFeign.class, "http://localhost:8080/principal/product/" + index);
		
		return pro.getProducto();
	}
	
	@Override
	public void callBorrar (int index) {
		IProductoFeign pro = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new JacksonEncoder())
				  .decoder(new JacksonDecoder())
				  .target(IProductoFeign.class, "http://localhost:8080/principal/borrar/" + index);
		
		pro.deleteProducto();
	}
	
	@Override
	public void callModificar (Producto pi) {
		IProductoFeign pro = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new JacksonEncoder())
				  .decoder(new JacksonDecoder())
				  .target(IProductoFeign.class, "http://localhost:8080/principal/modificar");

		pro.modProducto(pi);
	}	

}