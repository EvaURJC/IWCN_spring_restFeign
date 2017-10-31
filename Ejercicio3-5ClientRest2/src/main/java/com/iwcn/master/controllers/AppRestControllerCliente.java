package com.iwcn.master.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iwcn.master.model.Producto;
import com.iwcn.master.services.FeignDataService;

@Controller
public class AppRestControllerCliente {
	
	@Autowired
    private FeignDataService feignClient;

	@RequestMapping("/denied")
    public ModelAndView accessDeniedPage() {
		return new ModelAndView("denied_template");
    }
	
	// Vista Principal
	@RequestMapping("/")
	public ModelAndView nuevoPrincipal() {
		return new ModelAndView("nuevoPrincipal_template");
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login_template");
	}
	
	@RequestMapping("/salir")
	public ModelAndView logout() {
		return new ModelAndView("logout_template");
	}
		
	//Vista principal
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping("/principal")
	public ModelAndView principal() {
		return new ModelAndView("principal_template");
	}
	
	// Vista que contiene Lista de Productos
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@RequestMapping("/principal/list")
    public ModelAndView listaProducto() {
		return new ModelAndView("list_template").addObject("prods", feignClient.callLista());
    }
    
	// Vista que contiene el Formulario
	@Secured( "ROLE_ADMIN" )
	@GetMapping("/principal/new")
    public ModelAndView nuevoProducto(Model model, Producto pi) {
    	return new ModelAndView("newProduct_template");
    }
	
    // Vista que muestra que el producto se ha añadido
	@Secured( "ROLE_ADMIN" )
	@PostMapping("/principal/fin")
    public ModelAndView nuevoProducto2(@Valid Producto pi) {
		feignClient.callNuevo(pi);
    	return new ModelAndView("fin_template").addObject("producto", pi.getNombre());
    }
    
    // Vista del producto en si
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
    @RequestMapping("/principal/product/{optio}")
    public ModelAndView mostrarProducto(@PathVariable String optio)  {
    	int index = Integer.parseInt(optio);
    	Producto p = feignClient.callProducto(index);
    	return new ModelAndView("product_template").addObject("nombre", p.getNombre()).addObject("codigo", 
    			p.getCodigo()).addObject("descripcion", p.getDescripcion()).addObject("precio", p.getPrecio());
    }
    
    // Vista que contiene Lista de Productos
	@Secured( "ROLE_ADMIN" )
    @RequestMapping("/principal/borrar/{optio}")
    public ModelAndView borrarProducto(@PathVariable String optio) {
    	int index = Integer.parseInt(optio);
    	feignClient.callBorrar(index);
    	return new ModelAndView("borrar_template");
    }
    
    // Vista que contiene el formulario para editar el producto
    @Secured( "ROLE_ADMIN" )
    @RequestMapping("/principal/editar/{optio}")
    public ModelAndView editarProducto(@PathVariable String optio, Model model) {
    	int index = Integer.parseInt(optio);
    	Producto p = feignClient.callProducto(index);
    	model.addAttribute(p);
    	return new ModelAndView("editar_template").addObject("nombre", p.getNombre());
    }  
    
    // Vista que muestra que el producto se ha editado
    @Secured( "ROLE_ADMIN" )
    @RequestMapping("/principal/modificar")
    public ModelAndView modificarProducto(@Valid Producto pi) {
    	feignClient.callModificar(pi);
    	return new ModelAndView("modificar_template").addObject("nombre", pi.getNombre());
    }
    
}