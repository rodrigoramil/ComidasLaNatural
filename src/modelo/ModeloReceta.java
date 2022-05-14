package modelo;

import java.io.Serializable;

public class ModeloReceta  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ingrediente;
	float cantidad;

	public ModeloReceta(String ingrediente, float cantidad) {
		super();
		this.ingrediente = ingrediente;
		this.cantidad = cantidad;
	}
	
	public ModeloReceta() {
		super();
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ModeloReceta [ingrediente=" + ingrediente + ", cantidad=" + cantidad + "]";
	}
	
	
}

