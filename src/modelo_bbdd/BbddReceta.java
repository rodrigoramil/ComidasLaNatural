package modelo_bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.ModeloReceta;
import vista.Recetario;

public class BbddReceta {

	private static Connection connection = null;
	private static Conexion conexion = null;
	private static PreparedStatement sentenciaRecetas = null;
	private static ArrayList<ModeloReceta> arrayVentanaReceta = null;
	
	
	private static String nombreRecetaSeleccionada; 	// <--- BORRAR

	
//	public static void listarRecetas() {
//		conexion = new Conexion();
//		connection = conexion.obtenerConexion();
//		arrayVentanaReceta = new ArrayList<ModeloReceta>();
//		
//		try {
//			int recetaSelecionada = Recetario.recetaSeleccionada();			
//			for (int i = 0; i < Recetario.getArrayRecetas().size(); i++) {
//				if (recetaSelecionada==i) {
//					nombreRecetaSeleccionada=Recetario.getArrayRecetas().get(i).getNombreReceta();
//				}
//			}
//			sentenciaRecetas = connection.prepareStatement("Select A.NombreProducto, R.NombreReceta, R.Elaboracion, I.Cantidad  from Recetas R, Ingredientes I, Almacen A where r.NombreReceta=? and R.IdReceta = I.IdReceta and I.IDPRODUCTO = A.IDPRODUCTO");
//			sentenciaRecetas.setString(1, nombreRecetaSeleccionada);
//			ResultSet rsReceta = sentenciaRecetas.executeQuery();
//
//			while (rsReceta.next()) {
//				ModeloReceta recetas = new ModeloReceta(rsReceta.getString("NombreProducto"),rsReceta.getInt("Cantidad"), rsReceta.getString("Elaboracion"), rsReceta.getString("NombreReceta"));
//				arrayVentanaReceta.add(recetas);
//			}
//		}
//
//		 catch (SQLException e) {
//			System.out.println("Error en gestionPedidosClientes SentenciasSQL");
//			System.out.println(e.getMessage());
//		}
//	}

public static void insertarCliente(int idProducto, int idReceta, float cantidad, String elaboracion, String nombreReceta)  throws SQLException{
		// crea la receta 
		String SQLReceta = "insert into recetas(NombreReceta,elaboracion) values (?,?))";
		sentenciaRecetas = connection.prepareStatement(SQLReceta);
		sentenciaRecetas.setString(1, nombreReceta);
		sentenciaRecetas.setString(2, elaboracion);
		sentenciaRecetas.executeUpdate();
		// aqui se busca idreceta al cual asociar los ingredientes
		String SQLIdReceta = "Select idReceta from recetas where nombreReceta = ?";
		sentenciaRecetas = connection.prepareStatement(SQLIdReceta);
		sentenciaRecetas.setString(1, nombreReceta);
		
		int bbddIdReceta = sentenciaRecetas.executeUpdate();
		
		//con el idreceta, agregamos ingredientes
		String SQLIngrediente = "insert into ingredientes(IdReceta, IdProducto,Cantidad) values (?,?,?,?))";
		sentenciaRecetas = connection.prepareStatement(SQLIngrediente);
		sentenciaRecetas.setInt(1, idProducto);
		sentenciaRecetas.setInt(2, bbddIdReceta);
		sentenciaRecetas.setFloat(3, cantidad);
		sentenciaRecetas.executeUpdate();
	
	}
	public static ArrayList<ModeloReceta> getArrayVentanaReceta() {
		return arrayVentanaReceta;
	}

	public static String getNombreRecetaSeleccionada() {
		return nombreRecetaSeleccionada;
	}
	
	
	

}
