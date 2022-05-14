package modelo;

import java.io.Serializable;

public class ModeloPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2144175894579284069L;
	
	String comidaBebida;
	String cantidad;
	String precio;
	
	public ModeloPedido(String comidaBebida, String cantidad, String precio) {
		super();
		this.comidaBebida = comidaBebida;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public ModeloPedido() {
		super();
	}

	public String getComidaBebida() {
		return comidaBebida;
	}

	public void setComidaBebida(String comidaBebida) {
		this.comidaBebida = comidaBebida;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

}
