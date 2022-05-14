package modelo_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreacionBBDD {	

	private Statement stmt=null;
	private String usuario = "root";
	private String pass = "";
	
	public void creacionBBDD() {
	
		Connection con = null;
		String sURL = "jdbc:mysql://localhost:3306/";
		
		try {
		  con = DriverManager.getConnection(sURL, usuario, pass);
		  stmt = con.createStatement();
		  crearBBDD();
		  
		  // Tablas sin relaci�n
		  crearTablaUsuarios();
		  crearTablaGanancias();
		  crearTablaCliente();
		  crearTablaGasto();
		  crearTablaUnidadMedidaProducto();
		  crearTablaTipoReceta();
		  crearTablaDisponibilidadReceta();
		  
		  // Tablas con relaci�n
		  crearTablaRecetas();  
		  crearTablaAlmacen();		  		  
		  crearTablaTrabajoUsuariosGastos();
		  crearTablaTrabajoUsuariosGanancias();
		  crearTablaPedidoCliente();
		  crearTablaPedidos();		  
		  crearTablaIngredientes();
		  crearTablaCompraProductos();
		  
		  // Cliente tipo Mesa
//		  crearMesas(); 	// <-- Borrar llamarla desde una comprobaci�n en el array
		  
		  System.out.println("Se ha generado la base de datos");
		  
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(Vista_Login.getPanel_login(), "Error en la conexi�n con la Base de Datos");
			System.out.println("Error en la conexi�n:" + e.toString() );
		} finally {
		  try {
		    // Cerramos posibles conexiones abiertas
		    if (con!=null) con.close();
		  } catch (Exception e) {
			 // JOptionPane.showMessageDialog(Ventana.getPanel_login(), "Error cerrando conexiones con la Base de Datos");
			  System.out.println("Error cerrando conexiones: "+ e.toString());
		  } 
		}
	}
	
	public void crearBBDD() throws SQLException {
		stmt.execute("CREATE DATABASE IF NOT EXISTS bbdd_comidas_la_natural");
		stmt.execute("USE bbdd_comidas_la_natural;");
	}
	
	
	public void crearTablaUsuarios() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Usuarios (NombreUsuario VARCHAR(45) NOT NULL, Contrasena VARCHAR(45) NOT NULL, Rol ENUM('Administrador', 'Cocina', 'Venta') NOT NULL, PRIMARY KEY(NombreUsuario))ENGINE=INNODB;");
	}
	
	public void crearTablaGanancias() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Ganancias (IdPedido INT NOT NULL AUTO_INCREMENT, FechaPedido DATETIME NOT NULL, HoraPedido  DATETIME NOT NULL, GananciaPedido REAL NOT NULL, PRIMARY KEY(IdPedido))ENGINE=INNODB;");
	}
	
	public void crearTablaCliente() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Cliente (IdCliente INT NOT NULL AUTO_INCREMENT, NombreCliente VARCHAR(45) NOT NULL, Telefono VARCHAR(9) NOT NULL, PRIMARY KEY(IdCliente))ENGINE=INNODB;");
	}
	
	public void crearTablaGasto() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Gasto (IdCompraProductos INT NOT NULL AUTO_INCREMENT, CompraHecha boolean NOT NULL, FechaCompra datetime NOT NULL, GastoCompra real not null, PRIMARY KEY(IdCompraProductos))ENGINE=INNODB;");
	}
	
	public void crearTablaUnidadMedidaProducto() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS UnidadMedidaProducto (IdUnidadMedida INT NOT NULL AUTO_INCREMENT, UnidadMedida ENUM('Kg','Ud','L') NOT NULL, PRIMARY KEY(IdUnidadMedida))ENGINE=INNODB;");
	}
	
	public void crearTablaTipoReceta() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS TipoReceta (IdTipo INT NOT NULL AUTO_INCREMENT, Tipo ENUM('Comida', 'Bebida') NOT NULL, PRIMARY KEY(IdTipo))ENGINE=INNODB;");
	}
	
	public void crearTablaDisponibilidadReceta() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS DisponibilidadReceta (IdDisponibilidad INT NOT NULL AUTO_INCREMENT, Estado ENUM('Disponible', 'No Disponible', 'En Elaboraci�n') NOT NULL, PRIMARY KEY(IdDisponibilidad))ENGINE=INNODB;");
	}
		
	public void crearTablaRecetas() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Recetas (IdReceta INT NOT NULL AUTO_INCREMENT, IdTipo int NOT NULL, IdDisponibilidad INT NOT NULL, NombreReceta VARCHAR(45) NOT NULL unique, PrecioVenta Float NULL, Elaboracion TEXT NULL, PRIMARY KEY(IdReceta), constraint fkIdTipo foreign key(IdTipo) references TipoReceta(IdTipo), constraint fkIdDisponibilidad foreign key(IdDisponibilidad) references DisponibilidadReceta(IdDisponibilidad))ENGINE=INNODB;");
	}
	
	public void crearTablaAlmacen() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Almacen (IdProducto INT NOT NULL AUTO_INCREMENT, NombreProducto VARCHAR(45) NOT NULL UNIQUE, Cantidad FLOAT NULL, IdUnidadMedida Int NULL, CantidadMinima FLOAT NULL, CantidadMaxima FLOAT NULL, PRIMARY KEY(idProducto), constraint fkUnidadMedida foreign key(IdUnidadMedida) references UnidadMedidaProducto(IdUnidadMedida))ENGINE=INNODB;");
	}

	public void crearTablaTrabajoUsuariosGastos() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS TrabajoUsuariosGastos (NombreUsuario VARCHAR(45) NOT NULL, IdCompraProductos int NOT NULL, constraint fkNombreUsuarioGastos foreign key(NombreUsuario) references Usuarios(NombreUsuario), constraint fkIdCompraProductos foreign key(IdCompraProductos) references Gasto(IdCompraProductos))ENGINE=INNODB;");
	}
	
	public void crearTablaTrabajoUsuariosGanancias() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS TrabajoUsuariosGanancias (NombreUsuario VARCHAR(45) NOT NULL, IdPedido INT NOT NULL , constraint fkNombreUsuarioGanancia foreign key(NombreUsuario) references Usuarios(NombreUsuario), constraint fkIdPedidoGanancias foreign key(IdPedido) references Ganancias(IdPedido))ENGINE=INNODB;");
	}
	
	public void crearTablaPedidoCliente() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS PedidoCliente (IdPedido INT NOT NULL, IdCliente INT NOT NULL, constraint fkIdPedido foreign key(IdPedido) references Ganancias(IdPedido), constraint fkIdCliente foreign key(IdCliente) references Cliente (IdCliente))ENGINE=INNODB;");
	}
	
	public void crearTablaPedidos() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Pedidos (IdReceta INT NOT NULL, IdPedido INT NOT NULL, CantidadRecetaVenta int not null, constraint fkIdReceta foreign key(IdReceta) references Recetas(IdReceta), constraint fkIdPedidos foreign key(IdPedido) references Ganancias(IdPedido))ENGINE=INNODB;");
	}
		
	public void crearTablaIngredientes() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Ingredientes (IdReceta INT NOT NULL, IdProducto INT NOT NULL, Cantidad float not null, constraint fkIdRecetas foreign key(IdReceta) references Recetas(IdReceta), constraint fkIdIdProductoAlmacenes foreign key(IdProducto) references Almacen(IdProducto))ENGINE=INNODB;");
	}
	
		public void crearTablaCompraProductos() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS CompraProductos(IdCompraProductos INT NOT NULL, IdProducto INT NOT NULL, CantidadCompraProducto float not null, PrecioCompraProducto float, PrecioUnitarioProducto float, constraint fkIdCompraProductosGastos foreign key(IdCompraProductos) references Gasto(IdCompraProductos), constraint fkIdProductoAlmacen foreign key(IdProducto) references Almacen(IdProducto))ENGINE=INNODB;");
	}
		
		
		
}		




/*		
	public void crearMesas() throws SQLException  {
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 1');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 2');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 3');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 4');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 5');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 6');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 7');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 8');");
		stmt.execute("INSERT INTO Cliente (NombreCliente, Telefono) values ('Elena Nito','123456789');");
		stmt.execute("INSERT INTO Cliente (NombreCliente, Telefono) values ('Armando Paredes','123456789');");
		stmt.execute("INSERT INTO Cliente (NombreCliente, Telefono) values ('Pablo Garcia','123456789');");
		
		stmt.execute("Insert into Usuarios(NombreUsuario, Contrasena, Rol) values('','','Administrador');");
		stmt.execute("INSERT INTO Usuarios (NombreUsuario, Contrasena, Rol) values ('pepe','123','Administrador');");
		stmt.execute("INSERT INTO Usuarios (NombreUsuario, Contrasena, Rol) values ('lolo','123','Venta');");
		stmt.execute("INSERT INTO Usuarios (NombreUsuario, Contrasena, Rol) values ('fede','123','Venta');");
		stmt.execute("Insert into Usuarios(NombreUsuario, Contrasena, Rol) values('juan','123','Cocina');");
		stmt.execute("Insert into Usuarios(NombreUsuario, Contrasena, Rol) values('kiko','123','Cocina');");
		
		
		
		
	}
*/
		
			
	
	
	
/*	
	public void crearTablaUnidadMedidaProducto() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS UnidadMedidaProducto ("
				+ "IdUnidadMedida INT NOT NULL AUTO_INCREMENT, "
				+ "UnidadMedida ENUM ('Kg','Ud','L') NOT NULL, "
				+ "PRIMARY KEY (IdUnidadMedida)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaAlmacen() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Almacen("
				+ "IdProducto INT NOT NULL AUTO_INCREMENT, "
				+ "NombreProducto VARCHAR(45) NOT NULL UNIQUE, "
				+ "Cantidad FLOAT NOT NULL, "
				+ "IdUnidadMedida INT NOT NULL, "
				+ "CantidadMinima FLOAT NOT NULL, "
				+ "CantidadMaxima FLOAT NOT NULL, "
				+ "PRIMARY KEY(idProducto)"
				+ "constraint fkIdUnidadMedida foreign key(IdUnidadMedida) references UnidadMedidaProducto(IdUnidadMedida)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaRecetas() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Recetas("
				+ "IdReceta INT NOT NULL AUTO_INCREMENT, "
				+ "Tipo VARCHAR(45) NOT NULL, "
				+ "NombreReceta VARCHAR(45) NOT NULL, "
				+ "Estado ENUM('Disponible', 'No Disponible') NOT NULL, "
				+ "PrecioVenta FLOAT NOT NULL, "
				+ "Elaboracion TEXT NULL, "
				+ "PRIMARY KEY(IdReceta)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaUsuarios() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Usuarios("
				+ "NombreUsuario VARCHAR(45) NOT NULL, "
				+ "Contrasena VARCHAR(45) NOT NULL, "
				+ "Rol ENUM('Administrador', 'Cocina', 'Venta') NOT NULL, "
				+ "PRIMARY KEY(NombreUsuario)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaGanancias() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Ganancias("
				+ "IdPedido INT NOT NULL AUTO_INCREMENT, "
				+ "FechaPedido DATETIME NOT NULL, "
				+ "HoraPedido  FLOAT NOT NULL, "
				+ "GananciaPedido REAL NOT NULL, "
				+ "PRIMARY KEY(IdPedido)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaCliente() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Cliente("
				+ "IdCliente INT NOT NULL AUTO_INCREMENT, "
				+ "NombreCliente VARCHAR(45) NOT NULL unique, "
				+ "Telefono INT(9) NOT NULL, "
				+ "PRIMARY KEY(IdCliente)"
				+ ")ENGINE=INNODB;");
	}

	public void crearTablaGasto() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Gasto("
				+ "IdCompraProductos INT NOT NULL AUTO_INCREMENT, "
				+ "CompraHecha BOOLEAN NOT NULL, "
				+ "FechaCompra DATETIME NOT NULL, "
				+ "GastoCompra FLOAT not null, "
				+ "PRIMARY KEY(IdCompraProductos)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaTrabajoUsuariosGastos() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS TrabajoUsuariosGastos("
				+ "NombreUsuario VARCHAR(45) NOT NULL, "
				+ "IdCompraProductos INT NOT NULL, "
				+ "constraint fkNombreUsuarioGastos foreign key(NombreUsuario) references Usuarios(NombreUsuario), "
				+ "constraint fkIdCompraProductos foreign key(IdCompraProductos) references Gasto(IdCompraProductos)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaTrabajoUsuariosGanancias() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS TrabajoUsuariosGanancias("
				+ "NombreUsuario VARCHAR(45) NOT NULL, "
				+ "IdPedido INT NOT NULL, "
				+ "constraint fkNombreUsuarioGanancia foreign key(NombreUsuario) references Usuarios(NombreUsuario), "
				+ "constraint fkIdPedidoGanancias foreign key(IdPedido) references Ganancias(IdPedido)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaPedidoCliente() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS PedidoCliente("
				+ "IdPedido INT NOT NULL, "
				+ "IdCliente INT NOT NULL, "
				+ "constraint fkIdPedido foreign key(IdPedido) references Ganancias(IdPedido), "
				+ "constraint fkIdCliente foreign key(IdCliente) references Cliente(IdCliente)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaPedido() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Pedido("
				+ "IdReceta INT NOT NULL, "
				+ "IdPedido INT NOT NULL, "
				+ "CantidadRecetaVenta int not null, "
				+ "constraint fkIdReceta foreign key(IdReceta) references Recetas(IdReceta), "
				+ "constraint fkIdPedidos foreign key(IdPedido)references Ganancias(IdPedido)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaCompraProductos() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS CompraProductos("
				+ "IdCompraProductos INT NOT NULL, "
				+ "IdProducto INT NOT NULL, "
				+ "CantidadCompraProducto FLOAT not null, "
				+ "PrecioCompraProducto FLOAT not null, "
				+ "PrecioMedioCompraProducto FLOAT not null, "
				+ "constraint fkIdCompraProductosGastos foreign key(IdCompraProductos) references Gasto(IdCompraProductos), "
				+ "constraint fkIdProductoAlmacen foreign key(IdProducto)  references Almacen(IdProducto)"
				+ ")ENGINE=INNODB;");
	}
	
	public void crearTablaIngredientes() throws SQLException {
		stmt.execute("CREATE TABLE IF NOT EXISTS Ingredientes("
				+ "IdReceta INT NOT NULL, "
				+ "IdProducto INT NOT NULL, "
				+ "Cantidad FLOAT not null, "
				+ "constraint fkIdRecetas foreign key(IdReceta) references Recetas(IdReceta), "
				+ "constraint fkIdIdProductoAlmacenes foreign key(IdProducto) references Almacen(IdProducto)"
				+ ") ENGINE=INNODB;");
	}
	
	public void crearMesas() throws SQLException  {
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 1');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 2');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 3');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 4');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 5');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 6');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 7');");
		stmt.execute("INSERT INTO Cliente (NombreCliente) values ('Mesa 8');");
	}

	
}
*/	