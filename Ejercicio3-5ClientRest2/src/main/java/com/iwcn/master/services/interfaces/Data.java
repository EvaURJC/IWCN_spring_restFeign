package com.iwcn.master.services.interfaces;

import com.iwcn.master.model.Producto;

public interface Data {
	
	public Producto[] callLista ();
	
	public void callNuevo (Producto pi);
	
	public Producto callProducto(int index);
	
	public void callBorrar (int index);
	
	public void callModificar (Producto pi);
	
}
