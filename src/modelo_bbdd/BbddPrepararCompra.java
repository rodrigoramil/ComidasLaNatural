package modelo_bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ModeloPrepararCompra;

public class BbddPrepararCompra {
	
	private static Connection connection = null;
	private static Conexion conexion = null;
	private static PreparedStatement sentenciaRecetas = null;
	private static ArrayList<ModeloPrepararCompra> arrayPrepararCompra = null;

	String nombreProducto;
	float cantidadActual;
	float cantidadMinima;
	float cantidadMaxima;
	
	
	
	public static void listarPrepararCompra() {
		conexion = new Conexion();
		connection = conexion.obtenerConexion();		
		arrayPrepararCompra = new ArrayList<ModeloPrepararCompra>();		
		try {
			sentenciaRecetas = connection.prepareStatement("Select A.NombreProducto, C.CantidadCompraProducto from Almacen A, Compraproductos C  where A.IdProducto = C.IdProducto");
			ResultSet rs = sentenciaRecetas.executeQuery();			

			while (rs.next()) {
				ModeloPrepararCompra recetas = new ModeloPrepararCompra (rs.getString("NombreProducto"), rs.getFloat("CantidadCompraProducto"));
				arrayPrepararCompra.add(recetas);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al listar las recetas SentenciasSQL");
			System.out.println(e.getMessage());
		}
			
	}

	public static ArrayList<ModeloPrepararCompra> getArrayPrepararCompra() {
		return arrayPrepararCompra;
	}
	
}
