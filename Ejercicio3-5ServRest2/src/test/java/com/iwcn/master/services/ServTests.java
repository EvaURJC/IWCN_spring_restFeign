package com.iwcn.master.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.iwcn.master.services.DatabaseServices;
import com.iwcn.master.model.Producto;
import com.iwcn.master.repositories.ProductoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ServTests {
	
	private Producto pi;
	private ArrayList<Producto> todosProductos = new ArrayList<Producto> ();
	
	@Mock
	private ProductoRepository productoRepository;
	
	@InjectMocks
	private DatabaseServices dbServices2 = new DatabaseServices();
	
	@Before
	public void init(){
		pi = new Producto(1, "111", "111", "111",  new Integer (1));
		todosProductos.add(pi);
		
		when(productoRepository.selectAll()).thenReturn(todosProductos);
		when(productoRepository.findById(anyInt())).thenReturn(pi);
				
	}
	
	@Test
	public void getProductosTest()  throws ServiceException{
		ArrayList<Producto> todosProductos = dbServices2.getProductos();
		assertEquals(todosProductos.size(), 1);
		
	}
	
	@Test
	public void getProductoTest() throws ServiceException {

		Producto pi = dbServices2.findId(1);
		
		assertEquals(pi.getId(), 1);
		assertEquals(pi.getCodigo(), "111");
		assertEquals(pi.getNombre(), "111");
		assertEquals(pi.getDescripcion(), "111");
		assertEquals(pi.getPrecio(), new Integer(1));
		
	}

	@Test
	public void addProductoTest() throws ServiceException{

        assertEquals(dbServices2.getProductos().size(), 1);
        Producto p = new Producto(2, "222", "222", "222",  new Integer (2));
        dbServices2.addProducto(p);
        todosProductos.add(p);
        assertEquals(dbServices2.getProductos().size(), 2);
		
	}
	
	@Test
	public void deleteProductoTest(){

        assertEquals(dbServices2.getProductos().size(), 1);
        Producto p = dbServices2.findId(1);
        dbServices2.deleteProducto(2, "222", "222", "222",  new Integer (2));
        todosProductos.remove(p);
        assertEquals(dbServices2.getProductos().size(), 0);
		
	}

	@Test
	public void setProductoFromDBTests(){
        
        assertEquals(dbServices2.getProductos().size(), 1);
        Producto p = new Producto(2, "222", "222", "222", new Integer (2));
        dbServices2.setProducto("222", "222", "222", 2, new Integer (2));
        todosProductos.add(p);
        assertEquals(dbServices2.getProductos().size(), 2);
		
	}
}
