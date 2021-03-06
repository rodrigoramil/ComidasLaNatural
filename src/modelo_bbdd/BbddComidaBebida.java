package modelo_bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ModeloAlmacen;
import modelo.ModeloComidaBebida;
import modelo.ModeloPedido;

public class BbddComidaBebida {
	private static Connection connection = null;
	private static Conexion conexion = null;
	private static PreparedStatement sentenciaComidaBebida = null;
	private static ArrayList<ModeloComidaBebida> arrayComidaBebida = null;
	
	public static ArrayList<ModeloComidaBebida> listarComidaBebida() {
		conexion = new Conexion();
		connection = conexion.obtenerConexion();		
		arrayComidaBebida = new ArrayList<ModeloComidaBebida>();		
		try {
			sentenciaComidaBebida = connection.prepareStatement("Select R.IdReceta, R.NombreReceta, R.PrecioVenta, D.Estado, T.Tipo from recetas R, disponibilidadreceta D, tipoproducto T where D.IdDisponibilidad = R.IdDisponibilidad and R.IdTipo = T.IdTipo order by Estado");
			ResultSet rs = sentenciaComidaBebida.executeQuery();			

			while (rs.next()) {
				ModeloComidaBebida modelo = new ModeloComidaBebida(rs.getInt("IdReceta"), rs.getString("NombreReceta"), rs.getFloat("PrecioVenta"), rs.getString("Estado"), rs.getString("Tipo"));
				arrayComidaBebida.add(modelo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al listar las recetas SentenciasSQL");
			System.out.println(e.getMessage());
		}
		return arrayComidaBebida;	
	}


	

	public static ArrayList<ModeloComidaBebida> getArrayComidaBebida() {
		return arrayComidaBebida;
	}
	
}
